package com.farmand.foodapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.farmand.foodapp.R
import com.farmand.foodapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityMainBinding

    //Other
    private lateinit var navHost: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //NavController
        navHost = supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        //BottomNav
        binding.bottomNav.setupWithNavController(navHost.navController)
        //Show bottom nav
        navHost.navController.addOnDestinationChangedListener{ _, destination, _ ->
            if (destination.id == R.id.detailFragment){
                binding.bottomNav.visibility = View.GONE
            }else{
                binding.bottomNav.visibility = View.VISIBLE
            }

        }
    }

    override fun onNavigateUp(): Boolean {
        return navHost.navController.navigateUp() || super.onNavigateUp()
    }
}