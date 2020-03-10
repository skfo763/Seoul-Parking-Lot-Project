package com.skfo763.seoul_parking_lot.ui.map

import android.app.AlertDialog
import android.content.Intent
import android.location.Geocoder
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.skfo763.presentation.map.MapViewModel
import com.skfo763.presentation.model.MapDataModel
import com.skfo763.presentation.resource.ResourceState
import com.skfo763.seoul_parking_lot.BuildConfig
import com.skfo763.seoul_parking_lot.R
import com.skfo763.seoul_parking_lot.base.BaseFragment
import com.skfo763.seoul_parking_lot.databinding.FragmentMapBinding
import com.skfo763.seoul_parking_lot.utils.GpsManager
import com.skfo763.seoul_parking_lot.utils.PermissionProcessor
import dagger.android.support.AndroidSupportInjection
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapReverseGeoCoder

private const val PERMISSION_REQUEST_CODE = 1004
private const val GPS_REQUEST_CODE = 255

class MapFragment : BaseFragment<FragmentMapBinding, MapViewModel>(),
    MapReverseGeoCoder.ReverseGeoCodingResultListener {

    companion object {
        private const val TAG = "MapFragment"
        private const val NO_DATA_ALERT = "주변에 등록된 주차장 정보가 없어요 :("
        private const val UNDEFINED_ERR = "알 수 없는 에러로 데이터를 불러올 수 없습니다."
    }

    override fun layoutResId(): Int = R.layout.fragment_map
    override fun getViewModel(): Class<MapViewModel> = MapViewModel::class.java
    private val mapEventListener = MapEventListener(this)

    override fun executeInject() {
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= super.onCreateView(inflater, container, savedInstanceState)
        binding.mvMapFragMapView.setListener(mapEventListener)
        setCurrentLocationToMap()
        return view
    }

    override fun onStart() {
        super.onStart()
        viewModel.setMapForeground()
    }

    override fun initObserver() {
        viewModel.nearestInfo.observe(this.viewLifecycleOwner, Observer { data ->
            data?.let { handleDataState(it.resState, it.message, it.data) }
        })

        viewModel.targetPermissions.observe(this.viewLifecycleOwner, Observer { permissions ->
            requestTargetPermissions(permissions)
        })
    }

    private fun handleDataState(resState: ResourceState, message: String?, data: List<MapDataModel>?) {
        when(resState) {
            ResourceState.LOADING -> return
            ResourceState.SUCCESS -> {
                if(data.isNullOrEmpty())
                    Toast.makeText(requireContext(), NO_DATA_ALERT, Toast.LENGTH_SHORT).show()
            }
            ResourceState.ERROR ->
                Toast.makeText(requireContext(), message ?: UNDEFINED_ERR , Toast.LENGTH_SHORT).show()
        }
    }

    private fun setCurrentLocationToMap() {
        val gpsManager = GpsManager(requireContext())
        gpsManager.requestLocationWithPermission({ location ->
            viewModel.onAllPermissionGranted()
            location?.let {
                binding.mvMapFragMapView.setMapCenterPoint(it.latitude, it.longitude)
                requestApiWithAddress(it.latitude, it.longitude)
            } ?: kotlin.run {
                requestGpsService()
            }
        }) { deniedPermission ->
            viewModel.setViewStateWithDeniedPermission(deniedPermission)
        }
    }

    private fun requestGpsService() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.map_frag_dialog_gps_on_title))
            .setMessage(getString(R.string.map_frag_dialog_gps_on_message))
            .setCancelable(false)
            .setPositiveButton(R.string.dialog_confirm) { _, _ ->
                Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).apply {
                    startActivityForResult(this, GPS_REQUEST_CODE)
                }
            }.create()
            .show()
    }

    private fun requestTargetPermissions(permissions: List<String>) {
        if(permissions.isEmpty()) {
            /*val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            intent.data = Uri.fromParts("package", requireActivity().packageName, null)
            startActivityForResult(intent, PERMISSION_REQUEST_CODE)   */
        } else {
            requestPermissions(permissions.toTypedArray(), PERMISSION_REQUEST_CODE)
        }
    }

    fun setMarkerBasedOnCenterPoint(mapPoint: MapPoint? = null) {
        MapReverseGeoCoder(BuildConfig.API_KEY, mapPoint, this, requireActivity())
            .apply { startFindingAddress() }
    }

    private fun requestApiWithAddress(lat: Double, lng: Double) {
        try {
            val addrList = Geocoder(requireActivity()).getFromLocation(lat, lng, 5)
            if(!addrList.isNullOrEmpty()) {
                addrList[0].locality?.let {
                    viewModel.fetchNearestInfo(it)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode != PERMISSION_REQUEST_CODE) return

        val deniedPermission = PermissionProcessor.checkPermissionResult(permissions, grantResults)
        if(deniedPermission.isEmpty()) {
            viewModel.onAllPermissionGranted()
            setCurrentLocationToMap()
        } else {
            viewModel.setMapForegroundState(false)
            viewModel.setViewStateWithDeniedPermission(deniedPermission)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            GPS_REQUEST_CODE,
            PERMISSION_REQUEST_CODE -> setCurrentLocationToMap()
        }
    }

    override fun onReverseGeoCoderFailedToFindAddress(p0: MapReverseGeoCoder?) {}

    override fun onReverseGeoCoderFoundAddress(p0: MapReverseGeoCoder?, p1: String?) {
        p1?.let {
            val region = viewModel.getRegionName(p1) ?: return
            viewModel.fetchNearestInfo(region)
        }
    }
}
