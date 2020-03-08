package com.skfo763.seoul_parking_lot.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.skfo763.presentation.map.MapViewModel
import com.skfo763.presentation.model.MapDataModel
import com.skfo763.presentation.resource.ResourceState
import com.skfo763.seoul_parking_lot.BR
import com.skfo763.seoul_parking_lot.R
import com.skfo763.seoul_parking_lot.base.BaseFragment
import com.skfo763.seoul_parking_lot.databinding.FragmentMapBinding
import dagger.android.support.AndroidSupportInjection
import net.daum.mf.map.api.MapView

class MapFragment : BaseFragment<FragmentMapBinding, MapViewModel>(), MapContract.View {

    companion object {
        private const val TAG = "MapFragment"
        private const val NO_DATA_ALERT = "주변에 등록된 주차장 정보가 없어요 :("
    }

    private lateinit var mapPresenter: MapContract.Presenter
    override fun layoutResId(): Int = R.layout.fragment_map
    override fun getViewModel(): Class<MapViewModel> = MapViewModel::class.java
    override var mapView: MapView? = null

    override fun executeInject() {
        AndroidSupportInjection.inject(this)
        this.mapView = MapView(requireContext())
        mapPresenter = MapViewPresenter(this)
    }

    override fun initObserver() {
        viewModel.nearestInfo.observe(this, Observer { data ->
            data?.let { handleDataState(it.resState, it.message, it.data) }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        setMapView(binding)
        return view
    }

    private fun setMapView(binding: FragmentMapBinding) {
        val mapViewController = binding.mvMapFragMapView
        mapViewController.addView(mapView)
    }

    private fun handleDataState(
        resState: ResourceState,
        message: String?,
        data: List<MapDataModel>?
    ) {
        binding.setVariable(BR.isLoading, resState == ResourceState.LOADING)
        when(resState) {
            ResourceState.LOADING -> {}
            ResourceState.SUCCESS -> {
                data?.let { mapPresenter.setMarker(it) } ?: run {
                    Toast.makeText(requireContext(), NO_DATA_ALERT, Toast.LENGTH_SHORT).show()
                }
            }
            ResourceState.ERROR -> {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun loadData() {
        viewModel.fetchNearestInfo("송파")
    }
}
