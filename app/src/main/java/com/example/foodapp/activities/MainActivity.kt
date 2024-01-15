package com.example.foodapp.activities



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.foodapp.R
import com.example.foodapp.databinding.ActivityMainBinding
import com.example.foodapp.db.MealDatabase
import com.example.foodapp.fragment.CatagoryFragment
import com.example.foodapp.fragment.FavoritesFragment
import com.example.foodapp.fragment.HomeFragment
import com.example.foodapp.viewmodel.HomeViewModel
import com.example.foodapp.viewmodel.HomeViewModelFactory


class MainActivity : AppCompatActivity() {
    val viewModel : HomeViewModel by lazy {
        val mealDatabase = MealDatabase.getInstance(this)
        val homeViewModelProviderFactory = HomeViewModelFactory(mealDatabase)
        ViewModelProvider(this,homeViewModelProviderFactory)[HomeViewModel::class.java]
    }

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(HomeFragment())

       // val bottomNavigation = findViewById<BottomNavigationView>(com.example.foodapp.R.id.bottom_nav)
        //val navController = Navigation.findNavController(this, R.id.hots_fragment)

       // val navHostFragment = supportFragmentManager.findFragmentById(com.example.foodapp.R.id.hots_fragment) as NavHostFragment?
       // val navController = navHostFragment!!.navController


//        val navHostFragment =
//            supportFragmentManager.findFragmentById(com.example.foodapp.R.id.hots_fragment) as NavHostFragment?
//        var navController: NavController? = null
//        if (navHostFragment != null) {
//            navController = navHostFragment.navController
//        }
//        if (navController != null) {
//            setupActionBarWithNavController(this@MainActivity, navController)
//            setupWithNavController(bottomNavigation, navController)
//        }

      //  NavigationUI.setupWithNavController(bottomNavigation,navController)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(HomeFragment())
                R.id.favorites -> replaceFragment(FavoritesFragment())
                R.id.catagory -> replaceFragment(CatagoryFragment())

                else ->{

                }
            }
            true
        }

    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}