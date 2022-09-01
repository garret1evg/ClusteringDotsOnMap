package ua.chmutov.clusteringdotsonmap.model

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class HotspotData(
    val id: Int = 0,
    val lat: Double = 0.0,
    val lng: Double = 0.0
) : Parcelable, ClusterItem {
    override fun getPosition(): LatLng = LatLng(lat,lng)

    override fun getTitle(): String = id.toString()

    override fun getSnippet(): String = ""
}