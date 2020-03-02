package com.skfo763.seoul_parking_lot.ui.main

import android.app.Application
import com.skfo763.seoul_parking_lot.base.BaseViewModel

class MainViewModel(application: Application) : BaseViewModel<MainNavigator>(application) {
    fun actionClickTest(){
        getNavigator()?.test()
    }
}