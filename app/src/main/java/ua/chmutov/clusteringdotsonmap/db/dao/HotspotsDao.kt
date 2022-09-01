package ua.chmutov.clusteringdotsonmap.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ua.chmutov.clusteringdotsonmap.db.model.HotspotModel

@Dao
interface HotspotsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHotspot(corporationModel: HotspotModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHotspots(corporationModel: List<HotspotModel>)

    @Query("SELECT * FROM hotspots")
    fun getHotspots(): Flow<List<HotspotModel>>

    @Query("DELETE FROM hotspots")
    suspend fun deleteAll()

    @Transaction
    suspend fun replaceAll(corporationsModel: List<HotspotModel>) {
        deleteAll()
        insertHotspots(corporationsModel)
    }
}