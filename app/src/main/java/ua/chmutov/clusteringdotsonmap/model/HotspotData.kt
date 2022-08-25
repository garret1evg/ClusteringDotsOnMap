package ua.chmutov.clusteringdotsonmap.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HotspotData(
    val id: Int = 0,
    val lat: Double = 0.0,
    val lng: Double = 0.0
) : Parcelable