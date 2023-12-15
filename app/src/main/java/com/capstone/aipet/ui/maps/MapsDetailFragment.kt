package com.capstone.aipet.ui.maps

import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.capstone.aipet.R
import com.capstone.aipet.ui.home.HomeFragment
import com.capstone.aipet.ui.home.detaildog.DetailDogFragment

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsDetailFragment : Fragment() {

    private lateinit var googleMap: GoogleMap
    private var shelter: String = ""
    private var picture: String = ""
    private var phone: String = ""
    private var lat: Double? = null
    private var lon: Double? = null

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                getMyLocation()
            }
        }


    private val callback = OnMapReadyCallback { map ->
        googleMap = map

            shelter = arguments?.getString(EXTRA_SHELTER, "")!!
            picture = arguments?.getString(EXTRA_AVATAR, "")!!
            lat = arguments?.getDouble(EXTRA_LAT, 0.0)
            lon = arguments?.getDouble(EXTRA_LONG, 0.0)
            phone = arguments?.getString(EXTRA_PHONE, "")!!


            val shelterSpace = LatLng(lon!!, lat!!)
            Log.d("coordinat", "titik :${lat},${lon}")
            Log.d("coordinat", "selter :${shelter}")
            val markerOptions = MarkerOptions()
        googleMap.addMarker(
            markerOptions
                .position(shelterSpace)
                .title(shelter)
                .snippet(phone)
        )


//            Glide.with(this)
//                .asBitmap()
//                .load(picture)
//                .into(object : CustomTarget<Bitmap>() {
//                    override fun onResourceReady(
//                        resource: Bitmap,
//                        transition: Transition<in Bitmap>?
//                    ) {
//                        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(resource))
//        googleMap.addMarker(
//            markerOptions
//                .position(shelterSpace)
//                .title(shelter)
//                .snippet(description)
//        )
//
//                    }
//
//                    override fun onLoadCleared(placeholder: Drawable?) {
//                        // Jika gambar gagal dimuat, Anda dapat menangani kasus ini di sini
//                    }
//                })
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(shelterSpace, 15f))



        getMyLocation()

        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.uiSettings.isIndoorLevelPickerEnabled = true
        googleMap.uiSettings.isCompassEnabled = true
        googleMap.uiSettings.isMapToolbarEnabled = true


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps_detail, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Kembali ke HomeFragment saat tombol back ditekan
                parentFragmentManager.popBackStack()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater?.inflate(R.menu.map_options, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.normal_type -> {
                googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
                true
            }
            R.id.satellite_type -> {
                googleMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
                true
            }
            R.id.terrain_type -> {
                googleMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
                true
            }
            R.id.hybrid_type -> {
                googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
    private fun getMyLocation() {
        if (ContextCompat.checkSelfPermission(
                this.requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            googleMap.isMyLocationEnabled = true
        } else {
            requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }
    companion object{
        const val EXTRA_SHELTER = "extra_shelter"
        const val EXTRA_AVATAR = "extra_avatar"
        const val EXTRA_LAT = "extra_lat"
        const val EXTRA_LONG = "extra_long"
        const val EXTRA_PHONE = "extra_phone"
    }

}