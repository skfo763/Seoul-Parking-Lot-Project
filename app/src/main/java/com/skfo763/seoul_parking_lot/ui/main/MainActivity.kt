package com.skfo763.seoul_parking_lot.ui.main

import androidx.databinding.library.baseAdapters.BR
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.skfo763.seoul_parking_lot.R
import com.skfo763.seoul_parking_lot.base.BaseActivity
import com.skfo763.seoul_parking_lot.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    companion object {
        private const val TAG = "MainActivity"
    }

    private val viewModel = MainViewModel(application)

    override fun getLayoutId() = R.layout.activity_main

    override fun getViewModel(): MainViewModel {
        viewModel.setNavigator(this)
        return viewModel
    }

    override fun getBindingVariable() = BR.viewModel

    override fun setUp() {
        val host = supportFragmentManager.findFragmentById(R.id.fg_home_nav_container) as NavHostFragment
        NavigationUI.setupWithNavController(bnv_main_bottom_navigation_bar, host.navController)

    }

    override fun test() {
        Timber.tag(TAG).d("test")
    }
}
