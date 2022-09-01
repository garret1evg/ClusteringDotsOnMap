package ua.chmutov.clusteringdotsonmap.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hotspots")
data class HotspotModel(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int = 0,

    @ColumnInfo(name = "lat") val lat: Double = 0.0,
    @ColumnInfo(name = "lng") val lng: Double = 0.0
)
