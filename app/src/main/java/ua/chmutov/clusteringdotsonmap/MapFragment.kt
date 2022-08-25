package ua.chmutov.clusteringdotsonmap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ua.chmutov.clusteringdotsonmap.factory.MyViewModelFactory
import ua.chmutov.clusteringdotsonmap.model.HotspotData
import ua.chmutov.clusteringdotsonmap.viewmodel.MapViewModel

class MapFragment : Fragment(),OnMapReadyCallback {

    private val viewModel: MapViewModel by viewModels{
        MyViewModelFactory(context?.assets)
    }

    private lateinit var map: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_map, container, false).apply {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this@MapFragment)

//        mapFragment.getMapAsync{ googleMap->
//            googleMap.setOnMapClickListener {
//                val marker = MarkerOptions().apply {
//                    position(it)
//                    title("${it.latitude} ${it.longitude}")
//                }
//                googleMap.clear()
//                googleMap.animateCamera(CameraUpdateFactory.newLatLng(it))
//                googleMap.addMarker(marker)
//            }
//        }

        viewModel.hotspotDatas.observe(viewLifecycleOwner) {
            addPointMarkers(it)
        }

    }

    private fun addPointMarkers(list: List<HotspotData>) {
        list.forEach{ point ->
            map.addMarker(
                MarkerOptions()
                    .position(LatLng(point.lat, point.lng))
//                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.sevenmarker2))
//                    .zIndex(1.0f)
            )
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
    }
}