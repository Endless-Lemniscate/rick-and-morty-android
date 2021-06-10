package com.github.endless.lemniscate.rickandmorty.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.github.endless.lemniscate.rickandmorty.R
import com.github.endless.lemniscate.rickandmorty.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNavigation, navHostFragment.navController)

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.navigation_locations -> binding.bottomNavigation.visibility = View.VISIBLE
                R.id.navigation_characters -> binding.bottomNavigation.visibility = View.VISIBLE
                R.id.navigation_episodes -> binding.bottomNavigation.visibility = View.VISIBLE
                R.id.locationDetailsFragment -> binding.bottomNavigation.visibility = View.GONE
                R.id.characterDetailsFragment -> binding.bottomNavigation.visibility = View.GONE
                R.id.episodeDetailsFragment -> binding.bottomNavigation.visibility = View.GONE
            }
        }
    }
}