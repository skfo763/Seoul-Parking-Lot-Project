package com.skfo763.seoul_parking_lot.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.skfo763.presentation.ViewModelFactory
import com.skfo763.presentation.home.HomeViewModel
import com.skfo763.presentation.model.HomeDataModel
import com.skfo763.presentation.resource.ResourceState
import com.skfo763.seoul_parking_lot.R
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : Fragment() {

    companion object {
        private const val TAG = "HomeFragment"
    }

    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_home, container, false)
        AndroidSupportInjection.inject(this)
        homeViewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
        return view
    }

    override fun onStart() {
        super.onStart()
        setTestClickListener()
        homeViewModel.allBaseINfoLiveData.observe(this, Observer { data ->
            data?.let { handleDataState(it.resState, it.message, it.data) }
        })
    }

    private fun setTestClickListener() {
        test_button.setOnClickListener {
            homeViewModel.fetchAllBaseInfo()
        }
    }

    private fun handleDataState(resState: ResourceState, msg: String?, data: List<HomeDataModel>?) {
        when(resState) {
            ResourceState.LOADING -> {
                Log.d(TAG, "Loading...")
            }
            ResourceState.SUCCESS -> {
                Log.d(TAG, "SUCCESS: ${data?.toString()}")
            }
            ResourceState.ERROR -> {
                Log.d(TAG, "FAILURE: ${msg ?: "default_message"}")
            }
        }
    }
}
