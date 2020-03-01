package com.skfo763.seoul_parking_lot.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.skfo763.seoul_parking_lot.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpNavigation()
    }

     private fun setUpNavigation() {
         val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fg_home_nav_container) as NavHostFragment

         NavigationUI.setupWithNavController(
             bnv_main_bottom_navigation_bar, navHostFragment.navController)
    }
}
