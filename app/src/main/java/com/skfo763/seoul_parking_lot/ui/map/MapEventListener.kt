package com.skfo763.seoul_parking_lot.ui.map

import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class MapEventListener(private val mapFragment: MapFragment):
    MapView.MapViewEventListener, MapView.POIItemEventListener {

    override fun onMapViewDoubleTapped(mapView: MapView?, mapPoint: MapPoint?) {}

    override fun onMapViewInitialized(mapView: MapView?) {

    }

    override fun onMapViewDragStarted(mapView: MapView?, mapPoint: MapPoint?) {}

    override fun onMapViewMoveFinished(mapView: MapView?, mapPoint: MapPoint?) {}

    override fun onMapViewCenterPointMoved(mapView: MapView?, mapPoint: MapPoint?) {}

    override fun onMapViewDragEnded(mapView: MapView?, mapPoint: MapPoint?) {}

    override fun onMapViewSingleTapped(mapView: MapView?, mapPoint: MapPoint?) {}

    override fun onMapViewZoomLevelChanged(mapView: MapView?, mapPoint: Int) {}

    override fun onMapViewLongPressed(mapView: MapView?, mapPoint: MapPoint?) {}

    override fun onCalloutBalloonOfPOIItemTouched(mapView: MapView?, mapPoint: MapPOIItem?) {}

    override fun onCalloutBalloonOfPOIItemTouched(
        mapView: MapView?,
        mapPoint: MapPOIItem?,
        p2: MapPOIItem.CalloutBalloonButtonType?
    ) {}

    override fun onDraggablePOIItemMoved(mapView: MapView?, mapPoint: MapPOIItem?, p2: MapPoint?) {}

    override fun onPOIItemSelected(mapView: MapView?, mapPoint: MapPOIItem?) {}
}