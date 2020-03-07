package com.skfo763.seoul_parking_lot.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.skfo763.presentation.ViewModelFactory
import com.skfo763.presentation.map.MapViewModel
import com.skfo763.presentation.model.MapDataModel
import com.skfo763.presentation.resource.ResourceState
import com.skfo763.seoul_parking_lot.R
import com.skfo763.seoul_parking_lot.databinding.FragmentMapBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MapFragment : Fragment() {

    companion object {
        private const val TAG = "MapFragment"
    }

    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var mapViewModel: MapViewModel
    private lateinit var mapPresenter: MapViewPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            DataBindingUtil.inflate<FragmentMapBinding>(inflater, R.layout.fragment_map, container, false)
        executeInjection()
        setMapView(binding)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        mapViewModel.nearestInfo.observe(this, Observer { data ->
            data?.let { handleDataState(it.resState, it.message, it.data) }
        })
    }

    private fun executeInjection() {
        AndroidSupportInjection.inject(this)
        mapViewModel = ViewModelProvider(this, viewModelFactory).get(MapViewModel::class.java)
        mapPresenter = MapViewPresenter(requireContext())
    }

    private fun setMapView(binding: FragmentMapBinding) {
        val mapViewController = binding.mvMapFragMapView
        mapViewController.addView(mapPresenter.mapView)
    }

    private fun handleDataState(
        resState: ResourceState,
        message: String?,
        data: List<MapDataModel>?
    ) {

    }
}
