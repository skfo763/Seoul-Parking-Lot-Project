package com.skfo763.seoul_parking_lot.ui.map

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.skfo763.presentation.model.MapDataModel
import com.skfo763.seoul_parking_lot.R
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class CustomMapView : FrameLayout {

    companion object {
        private const val TAG = "MapViewPresenter"
        private const val DEFAULT_LAT: Float = 37.514482F
        private const val DEFAULT_LNG: Float = 127.105874F
        private const val DEFAULT_ZOOM_LEVEL: Int = 4
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0) { getAttrs(attrs) }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val mapView = MapView(context)
    private var defaultZoomLevel = DEFAULT_ZOOM_LEVEL

    init {
        this.addView(mapView)
    }

    private fun getAttrs(attr: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(
            attr, R.styleable.CustomMapView, 0, 0)
        try {
            val defaultLatitude =
                typedArray.getFloat(R.styleable.CustomMapView_defaultLatitude, DEFAULT_LAT).toDouble()
            val defaultLongitude =
                typedArray.getFloat(R.styleable.CustomMapView_defaultLongitude, DEFAULT_LNG).toDouble()
            defaultZoomLevel = typedArray.getInt(R.styleable.CustomMapView_defaultZoomLevel, DEFAULT_ZOOM_LEVEL)
            setMapCenterPoint(defaultLatitude, defaultLongitude, defaultZoomLevel)
        } finally { typedArray.recycle() }
    }

    fun setListener(listener: MapEventListener) {
        this.mapView.setMapViewEventListener(listener)
        this.mapView.setPOIItemEventListener(listener)
    }

    fun setMarker(data: List<MapDataModel>) {
        data.forEach {
            setParkingLotMarker(it)
        }
    }

    fun setMapCenterPoint(lat: Double, lng: Double, zoomLevel: Int = DEFAULT_ZOOM_LEVEL) {
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(lat, lng), true)
        mapView.setZoomLevel(zoomLevel, true)
    }

    private fun setParkingLotMarker(data: MapDataModel) {
        val marker = MapPOIItem().apply {
            itemName = data.name
            tag = data.parkingCode.toIntOrNull() ?: 1
            mapPoint = MapPoint.mapPointWithGeoCoord(data.latitude, data.longitude)
            markerType = MapPOIItem.MarkerType.BluePin
            selectedMarkerType = MapPOIItem.MarkerType.RedPin
        }
        mapView.addPOIItem(marker)
    }
}