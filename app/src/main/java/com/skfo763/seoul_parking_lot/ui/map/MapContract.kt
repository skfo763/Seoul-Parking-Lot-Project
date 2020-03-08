package com.skfo763.seoul_parking_lot.ui.map

import com.skfo763.presentation.model.MapDataModel
import net.daum.mf.map.api.MapView


interface MapContract {

    interface View {
        val mapView: MapView?
        fun loadData()
    }

    interface Presenter {
        fun setMarker(data: List<MapDataModel>)
    }
}