package com.example.a160420124_marcelladivaviorent_healthcareumc.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.a160420124_marcelladivaviorent_healthcareumc.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout


    init {
        instance = this
    }
    companion object {
        var instance:MainActivity ?= null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawerLayout)

        navController =
            (supportFragmentManager.findFragmentById(R.id.hostFragment) as
                    NavHostFragment).navController
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        val navView = findViewById<NavigationView>(R.id.navView)
        NavigationUI.setupWithNavController(navView, navController)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.setupWithNavController(navController)
        navController.addOnDestinationChangedListener{ _, destination, _ ->
            when(destination.id) {
                R.id.articleDetailFragment -> bottomNav.visibility = View.GONE
                R.id.doctorPracticeScheduleFragment -> bottomNav.visibility = View.GONE
                R.id.drugDetailFragment -> bottomNav.visibility = View.GONE
                else -> bottomNav.visibility = View.VISIBLE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(drawerLayout)
                || super.onSupportNavigateUp()
    }
}