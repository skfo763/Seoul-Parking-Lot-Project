package com.skfo763.seoul_parking_lot.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.skfo763.presentation.ViewModelFactory
import com.skfo763.presentation.map.MapViewModel
import com.skfo763.presentation.model.MapDataModel
import com.skfo763.presentation.resource.ResourceState
import com.skfo763.seoul_parking_lot.R
import dagger.android.support.AndroidSupportInjection
import net.daum.mf.map.api.MapView
import javax.inject.Inject

class MapFragment : Fragment() {

    companion object {
        private const val TAG = "MapFragment"
    }

    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var mapViewModel: MapViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_map, container, false)
        AndroidSupportInjection.inject(this)
        mapViewModel = ViewModelProvider(this, viewModelFactory).get(MapViewModel::class.java)
        setMapView(view)
        return view
    }

    override fun onStart() {
        super.onStart()
        mapViewModel.nearestInfo.observe(this, Observer { data ->
            data?.let { handleDataState(it.resState, it.message, it.data) }
        })
    }

    private fun setMapView(view: View) {
        val mapViewController = view.findViewById<ViewGroup>(R.id.mv_map_frag_map_view)
        mapViewController.addView(MapView(context))
    }

    private fun handleDataState(
        resState: ResourceState,
        message: String?,
        data: List<MapDataModel>?
    ) {

    }
}
