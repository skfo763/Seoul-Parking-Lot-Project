package com.skfo763.seoul_parking_lot.ui.cache

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.skfo763.presentation.ViewModelFactory
import com.skfo763.presentation.cache.CacheViewModel
import com.skfo763.presentation.model.CacheDataModel
import com.skfo763.presentation.resource.ResourceState
import com.skfo763.seoul_parking_lot.R
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_cache.*
import javax.inject.Inject

class CacheFragment : Fragment() {

    companion object {
        private const val TAG = "MapFragment"
    }

    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var cacheViewModel: CacheViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cache, container, false)
        AndroidSupportInjection.inject(this)
        cacheViewModel = ViewModelProvider(this, viewModelFactory).get(CacheViewModel::class.java)
        return view
    }

    override fun onStart() {
        super.onStart()
        setTestClickListener()
        cacheViewModel.cacheInfo.observe(this, Observer { data ->
            data?.let { handleDataState(it.resState, it.message, it.data) }
        })
    }

    private fun setTestClickListener() {
        test_button_three.setOnClickListener {
            cacheViewModel.getAllCacheInfo()
        }
    }

    private fun handleDataState(resState: ResourceState, msg: String?, data: List<CacheDataModel>?) {
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