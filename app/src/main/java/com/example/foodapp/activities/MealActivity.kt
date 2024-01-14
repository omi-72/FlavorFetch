package com.example.foodapp.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.databinding.ActivityMealBinding
import com.example.foodapp.fragment.HomeFragment
import com.example.foodapp.pojo.Meal
import com.example.foodapp.viewmodel.MealViewModel

class MealActivity : AppCompatActivity() {
    private lateinit var mealId : String
    private lateinit var mealName : String
    private lateinit var mealThumb : String

    private lateinit var binding: ActivityMealBinding
    private lateinit var mealMvvm : MealViewModel
    private lateinit var youTubeLink : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mealMvvm = ViewModelProviders.of(this)[MealViewModel::class.java]

        getMealInformationFromIntent()
        setInformationView()

        loadingCase()
        mealMvvm.getMealDetail(mealId)
        observerMealDetailsLiveData()

        onYouTubeImageClick()
    }

    private fun onYouTubeImageClick() {
        binding.imageViewYouTube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youTubeLink))
            startActivity(intent)
        }
    }

    private fun observerMealDetailsLiveData() {
        mealMvvm.observeMealDetailsLiveData().observe(this,object : Observer<Meal>{
            override fun onChanged(value: Meal) {
                onResponseCase()
                val meal = value
                binding.textViewCategory.text = "Category : ${meal.strCategory}"
                binding.textViewArea.text = "Area : ${meal.strArea}"
                binding.textViewInstructionSteps.text = meal.strInstructions

                youTubeLink = meal.strYoutube
            }

        })
    }

    private fun setInformationView() {
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.imageMealDetail)

        binding.collapsingToolbar.title = mealName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
    }

    private fun getMealInformationFromIntent() {
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }

    private fun loadingCase(){
        binding.progressBar.visibility = View.VISIBLE
        binding.addToFav.visibility = View.INVISIBLE
        binding.textViewInstruction.visibility = View.INVISIBLE
        binding.textViewCategory.visibility = View.INVISIBLE
        binding.textViewArea.visibility = View.INVISIBLE
        binding.imageViewYouTube.visibility = View.INVISIBLE

    }

    private fun onResponseCase(){
        binding.progressBar.visibility = View.INVISIBLE
        binding.addToFav.visibility = View.VISIBLE
        binding.textViewInstruction.visibility = View.VISIBLE
        binding.textViewCategory.visibility = View.VISIBLE
        binding.textViewArea.visibility = View.VISIBLE
        binding.imageViewYouTube.visibility = View.VISIBLE
    }
}