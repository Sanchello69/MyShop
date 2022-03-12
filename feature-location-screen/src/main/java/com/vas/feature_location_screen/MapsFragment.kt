package com.vas.feature_location_screen

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.vas.feature_location_screen.databinding.FragmentMapsBinding

class MapsFragment : Fragment() {

    private lateinit var binding: FragmentMapsBinding
    private lateinit var gMap: GoogleMap

    private val MOSKAU =
        LatLngBounds(LatLng(55.574487, 37.359872), LatLng(55.901914, 37.868179)) //границы Москвы

    private val MOSKAU1 = LatLng(55.753215, 37.622504)

    private val markers = listOf(
        MarkerModel("М.Видео" ,LatLng(55.805336, 37.635565)),
        MarkerModel("М.Видео" ,LatLng(55.761651, 37.568382)),
        MarkerModel("М.Видео" ,LatLng(55.870385, 37.637941)),
        MarkerModel("М.Видео" ,LatLng(55.803545, 37.801362)),
        MarkerModel("М.Видео" ,LatLng(55.806088, 37.395311)),
        MarkerModel("М.Видео" ,LatLng(55.648890, 37.746535)),
        MarkerModel("М.Видео" ,LatLng(55.793287, 37.604039)),
        MarkerModel("М.Видео" ,LatLng(55.786454, 37.664197)),
        MarkerModel("М.Видео" ,LatLng(55.766471, 37.622289)),
        MarkerModel("М.Видео" ,LatLng(55.748265, 37.581406)),
        MarkerModel("М.Видео" ,LatLng(55.790782, 37.5280465))
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMapsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync { googleMap ->
            gMap = googleMap
            googleMap.uiSettings?.isMapToolbarEnabled = false
            googleMap.uiSettings?.isMyLocationButtonEnabled = false
            addMarkers(googleMap)
            limitMaps(googleMap)
        }

        initButtonLocation()
        initButtonBack()

    }

    private fun initButtonBack() {
        binding.backImageView.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }
    }

    private fun initButtonLocation() {
        binding.buttonLocation.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) { //если получено разрешение
                if (gMap != null) {
                    gMap.isMyLocationEnabled = true
                }
            }
            else requestPermission()
        }
    }

    private fun limitMaps(googleMap: GoogleMap) {
        googleMap.apply {
            moveCamera(CameraUpdateFactory.newLatLngZoom(MOSKAU1, 10.toFloat())) //положение карты при запуске (без анимации)
            setLatLngBoundsForCameraTarget(MOSKAU); //ограничиваем область видимости карты
        }
    }

    private fun addMarkers(googleMap: GoogleMap) {
        markers.forEach { place ->
            googleMap.addMarker(
                MarkerOptions()
                    .title(place.name)
                    .position(place.latLng)
            )
        }
    }

    private fun requestPermission(){
        //запрос на использование геолокации
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
            1
        )
    }
}