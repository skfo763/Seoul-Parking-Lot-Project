package com.skfo763.seoul_parking_lot.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.skfo763.presentation.ViewModelFactory
import com.skfo763.presentation.home.HomeViewModel
import com.skfo763.presentation.model.HomeDataModel
import com.skfo763.presentation.resource.ResourceState
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
        return view
    }

    override fun onStart() {
        super.onStart()
        viewModel.allBaseINfoLiveData.observe(this, Observer { data ->
            data?.let { this.testCall(it.resState, it.message, it.data)}
        })
    }

    private fun testCall(resState: ResourceState, message: String?, data: List<HomeDataModel>?) {
        when(resState) {
            ResourceState.SUCCESS -> {
                Toast.makeText(context, "success", Toast.LENGTH_LONG).show()
                Log.d("HomeFragment", data?.toString())
            }
            ResourceState.ERROR -> {
                Toast.makeText(context, "fail", Toast.LENGTH_LONG).show()
                Log.d("HomeFragment", message)
            }
            ResourceState.LOADING -> {
                Toast.makeText(context, "loading", Toast.LENGTH_LONG).show()
                Log.d("HomeFragment", "loading...")
            }
        }
    }
}
