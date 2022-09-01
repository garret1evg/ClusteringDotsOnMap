package ua.chmutov.clusteringdotsonmap.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.chmutov.clusteringdotsonmap.db.dao.HotspotsDao
import ua.chmutov.clusteringdotsonmap.db.model.HotspotModel

@Database(entities = [HotspotModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun hotspotsDao(): HotspotsDao
}