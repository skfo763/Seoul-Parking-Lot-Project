package com.skfo763.seoul_parking_lot.ui.map

import android.content.Context
import com.skfo763.presentation.model.MapDataModel
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class MapViewPresenter(context: Context)
    : MapView.MapViewEventListener, MapView.POIItemEventListener {

    companion object {
        private const val DEFAULT_ZOOM_LEVEL = 4
    }

    private val _mapView = MapView(context)
    val mapView: MapView get() = _mapView

    init {
        _mapView.setMapViewEventListener(this)
        _mapView.setPOIItemEventListener(this)
    }

    private fun setMapCenterPoint(lat: Double, lng: Double) {
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(lat, lng), true)
        mapView.setZoomLevel(DEFAULT_ZOOM_LEVEL, true)
    }

    private fun setParkingLotMarker(data: MapDataModel) {
        val marker = MapPOIItem().apply {
            itemName = data.name
            tag = data.parkingCode.toIntOrNull() ?: 1
            mapPoint = MapPoint.mapPointWithGeoCoord(data.latitude, data.longitude)
            markerType = MapPOIItem.MarkerType.BluePin
            selectedMarkerType = MapPOIItem.MarkerType.RedPin
        }
        _mapView.addPOIItem(marker)
    }

    override fun onMapViewDoubleTapped(p0: MapView?, p1: MapPoint?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMapViewInitialized(p0: MapView?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMapViewDragStarted(p0: MapView?, p1: MapPoint?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMapViewMoveFinished(p0: MapView?, p1: MapPoint?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMapViewCenterPointMoved(p0: MapView?, p1: MapPoint?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMapViewDragEnded(p0: MapView?, p1: MapPoint?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMapViewSingleTapped(p0: MapView?, p1: MapPoint?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMapViewZoomLevelChanged(p0: MapView?, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMapViewLongPressed(p0: MapView?, p1: MapPoint?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCalloutBalloonOfPOIItemTouched(
        p0: MapView?,
        p1: MapPOIItem?,
        p2: MapPOIItem.CalloutBalloonButtonType?
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}