package com.skfo763.seoul_parking_lot.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.skfo763.seoul_parking_lot.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setNavigation()
    }

    private fun setNavigation() {
        val host = supportFragmentManager.findFragmentById(R.id.fg_home_nav_container)
                as? NavHostFragment ?: return
        NavigationUI.setupWithNavController(bnv_main_bottom_navigation_bar, host.navController)
    }
}
