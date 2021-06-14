package com.qualitestudios.memorr.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.*
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.qualitestudios.memorr.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.navHostFragment)
       setSupportActionBar(toolbar)
        toolbar.setupWithNavController(navController)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navView: BottomNavigationView = findViewById(R.id.bnv)




        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_dashboard,R.id.navigation_memories))
        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setupWithNavController(navController)
       // toolbar.inflateMenu(R.menu.savedeletemenu)






    }



}