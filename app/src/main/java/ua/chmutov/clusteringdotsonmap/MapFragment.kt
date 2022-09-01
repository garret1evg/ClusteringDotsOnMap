package ua.chmutov.clusteringdotsonmap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.room.Room
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.collections.MarkerManager
import com.google.maps.android.ktx.awaitMap
import com.google.maps.android.ktx.awaitMapLoad
import ua.chmutov.clusteringdotsonmap.db.AppDatabase
import ua.chmutov.clusteringdotsonmap.db.mapper.toHotspotData
import ua.chmutov.clusteringdotsonmap.factory.MyViewModelFactory
import ua.chmutov.clusteringdotsonmap.io.HotspotReader
import ua.chmutov.clusteringdotsonmap.model.HotspotData
import ua.chmutov.clusteringdotsonmap.viewmodel.MapViewModel
import java.io.IOException

class MapFragment : Fragment() {

    private val viewModel: MapViewModel by viewModels {
        MyViewModelFactory(requireContext().assets, Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, "database-name"
        ).build().hotspotsDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_map, container, false).apply {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        lifecycle.coroutineScope.launchWhenCreated {
            val googleMap = mapFragment.awaitMap()
            googleMap.awaitMapLoad()
            addClusters(googleMap, MarkerManager(googleMap))
        }
    }

    private suspend fun addClusters(map: GoogleMap, markerManager: MarkerManager) {
        val clusterManager = ClusterManager<HotspotData>(context, map, markerManager)
        map.setOnCameraIdleListener(clusterManager)
        viewModel.hotspotDatas.collect{ list ->
            clusterManager.addItems(list.map { it.toHotspotData() })
        }
//        try {
//            val hotspotsCSV = "csv/hotspots10k.csv"
//            val items = HotspotReader().read(requireContext().assets.open(hotspotsCSV))
//            clusterManager.addItems(items)
//        } catch (e: IOException) {
//            Toast.makeText(context, "Problem reading list of hotspots.", Toast.LENGTH_LONG).show()
//            e.printStackTrace()
//        }
    }

}