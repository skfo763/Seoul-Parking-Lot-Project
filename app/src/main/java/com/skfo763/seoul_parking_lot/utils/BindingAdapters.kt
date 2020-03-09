package com.skfo763.seoul_parking_lot.utils

import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.BindingAdapter
import com.skfo763.presentation.map.MapViewModel
import com.skfo763.presentation.model.MapDataModel
import com.skfo763.seoul_parking_lot.ui.map.CustomMapView

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("markerData")
    fun setMarkerData(mapView: CustomMapView, data: List<MapDataModel>?) {
        data?.let {
            mapView.setMarker(it)
        }
    }

    @JvmStatic
    @BindingAdapter("onRequestPermissionButtonClicked")
    fun AppCompatButton.onRequestPermissionButtonClicked(viewModel: MapViewModel?) {
        this.setOnClickListener {
            viewModel?.requestPermission(viewModel.deniedPermissions.value)
        }
    }
}