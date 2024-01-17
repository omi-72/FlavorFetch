package com.example.foodapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.activities.CategoryMealsActivity
import com.example.foodapp.activities.MainActivity
import com.example.foodapp.activities.MealActivity
import com.example.foodapp.adapter.CategoriesAdapter
import com.example.foodapp.adapter.MostPopularAdapter
import com.example.foodapp.bottomsheet.MealBottomSheetFragment
import com.example.foodapp.databinding.FragmentHomeBinding
import com.example.foodapp.pojo.Meal
import com.example.foodapp.pojo.MealsByCategory
import com.example.foodapp.viewmodel.HomeViewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel : HomeViewModel
    private lateinit var randomMeal : Meal
    private lateinit var popularItemsAdapter: MostPopularAdapter
    private lateinit var categoriesAdapter: CategoriesAdapter

    companion object{
        const val MEAL_ID = "com.example.foodapp.fragment.idMeal"
        const val MEAL_NAME = "com.example.foodapp.fragment.nameMeal"
        const val MEAL_THUMB = "com.example.foodapp.fragment.thumbMeal"
        const val CATEGORY_NAME = "com.example.foodapp.fragment.categoryName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // viewModel = ViewModelProviders.of(this)[HomeViewModel::class.java]
        viewModel = (activity as MainActivity).viewModel
        popularItemsAdapter = MostPopularAdapter()

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)


        return binding.root


    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preparePopularItemsRecyclerView()



        viewModel.getRandomMeal()
        observerRandomMeal()
        onRandomMealClick()

        viewModel.getPopularItems()
        observerPopularItemsLiveData()
        onPopularItemsClick()

        prepareCategoriesRecyclerView()
        viewModel.getCategories()
        observerCategoriesLiveData()
        onCategoryClick()

        onPopularItemsLongClick()

        onSearchIconClick()



    }



    private fun onSearchIconClick() {
        binding.imageSearch.setOnClickListener {

            val searchFragment = SearchFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, searchFragment)
            transaction.addToBackStack(null) // Optional: Add transaction to back stack
            transaction.commit()


    //findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
           //findNavController(requireView()).navigate(R.id.action_homeFragment_to_searchFragment);
              //   val  navController = Navigation.findNavController(context, R.id.action_homeFragment_to_searchFragment)

//            val navigate = Navigation.findNavController(requireActivity(), R.id.homeFragment)
//            navigate.navigate(R.id.searchFragment)
            //replaceFragment(SearchFragment())

           // findNavController().navigate(androidx.navigation.fragment.R.id.nav_host_fragment_container)

//            val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//            val navController = navHostFragment.navController
//            navController.navigate(R.id.action_homeFragment_to_searchFragment)
           // Navigation.findNavController(context, R.id.homeFragment).navigate(R.id.searchFragment);

           // val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            //val navController = navHostFragment.navController

            //val navController = binding.navHostFragment.findNavController().navigate(R.id.action_homeFragment_to_searchFragment)

//            val searchFragment = SearchFragment()
//            val transaction : FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction();
//            transaction.replace(R.id.nav_host_fragment, searchFragment)
//            transaction.commit()

           // Navigation.findNavController().navigate(R.id.action_homeFragment_to_searchFragment)

        }
    }

    private fun onPopularItemsLongClick() {
        popularItemsAdapter.onLongItemClick = { meal ->
            val mealBottomSheetFragment = MealBottomSheetFragment.newInstance(meal.idMeal)
            mealBottomSheetFragment.show(childFragmentManager,"Meal Info")

        }
    }

    private fun onCategoryClick() {
        categoriesAdapter.onItemClick = { category ->
            val intent = Intent(activity,CategoryMealsActivity::class.java)
            intent.putExtra(CATEGORY_NAME,category.strCategory)
            startActivity(intent)
        }
    }

    private fun prepareCategoriesRecyclerView() {
        categoriesAdapter = CategoriesAdapter()
        binding.recyclerViewCategories.apply {
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
            adapter = categoriesAdapter
        }
    }

    private fun observerCategoriesLiveData() {
        viewModel.observerCategoriesLiveData().observe(viewLifecycleOwner) { categories ->

                categoriesAdapter.setCategoryList(categories)
               // Log.d("test", category.strCategory)
            }
    }

    private fun onPopularItemsClick() {
        popularItemsAdapter.onItemClick = {meal ->
            val intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(MEAL_ID,meal.idMeal)
            intent.putExtra(MEAL_NAME,meal.strMeal)
            intent.putExtra(MEAL_THUMB,meal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun preparePopularItemsRecyclerView() {
        binding.recyclerViewOverPopular.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularItemsAdapter

        }
    }

    private fun observerPopularItemsLiveData() {
        viewModel.observerPopularItemsLiveData().observe(viewLifecycleOwner
        ) { mealList ->
            popularItemsAdapter.setMeals(mealsList = mealList as ArrayList<MealsByCategory>)
        }
    }

    private fun onRandomMealClick() {
        binding.randomMealCard.setOnClickListener {
            val intent = Intent(activity,MealActivity::class.java)
            intent.putExtra(MEAL_ID,randomMeal.idMeal)
            intent.putExtra(MEAL_NAME,randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB,randomMeal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun observerRandomMeal() {
        viewModel.observeRandomMealLiveData().observe(viewLifecycleOwner
        ) { meal ->
            Glide.with(this@HomeFragment)
                .load(meal!!.strMealThumb)
                .into(binding.imageRandomMeal)
            this.randomMeal = meal

        }
    }

}