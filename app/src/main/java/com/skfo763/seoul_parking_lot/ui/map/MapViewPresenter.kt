package com.skfo763.seoul_parking_lot.ui.map

import android.util.Log
import com.skfo763.presentation.model.MapDataModel
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class MapViewPresenter(val view: MapContract.View)
    : MapContract.Presenter, MapView.MapViewEventListener, MapView.POIItemEventListener {

    companion object {
        private const val TAG = "MapViewPresenter"
        private const val DEFAULT_ZOOM_LEVEL = 4
    }

    init {
        view.mapView?.setMapViewEventListener(this)
        view.mapView?.setPOIItemEventListener(this)
        setMapCenterPoint(37.516244, 127.112099)
    }

    override fun setMarker(data: List<MapDataModel>) {
        data.forEach {
            setParkingLotMarker(it)
        }
    }

    private fun setMapCenterPoint(lat: Double, lng: Double) {
        view.mapView?.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(lat, lng), true)
        view.mapView?.setZoomLevel(DEFAULT_ZOOM_LEVEL, true)
    }

    private fun setParkingLotMarker(data: MapDataModel) {
        val marker = MapPOIItem().apply {
            itemName = data.name
            tag = data.parkingCode.toIntOrNull() ?: 1
            mapPoint = MapPoint.mapPointWithGeoCoord(data.latitude, data.longitude)
            markerType = MapPOIItem.MarkerType.BluePin
            selectedMarkerType = MapPOIItem.MarkerType.RedPin
        }
        view.mapView?.addPOIItem(marker)
    }

    override fun onMapViewDoubleTapped(mapView: MapView?, mapPoint: MapPoint?) {
        
    }

    override fun onMapViewInitialized(mapView: MapView?) {
        Log.d(TAG, "map is initialized")
        view.loadData()
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