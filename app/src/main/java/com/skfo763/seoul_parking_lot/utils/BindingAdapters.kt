package com.skfo763.seoul_parking_lot.utils

import androidx.databinding.BindingAdapter
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
}