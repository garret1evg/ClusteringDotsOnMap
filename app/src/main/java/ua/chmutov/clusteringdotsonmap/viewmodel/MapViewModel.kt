package ua.chmutov.clusteringdotsonmap.viewmodel

import android.content.res.AssetManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.chmutov.clusteringdotsonmap.db.dao.HotspotsDao
import ua.chmutov.clusteringdotsonmap.db.model.HotspotModel
import ua.chmutov.clusteringdotsonmap.model.HotspotData
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets

class MapViewModel(
    val assetManager: AssetManager?,
    val hotspotsDao: HotspotsDao
) : ViewModel() {

    val hotspotDatas = hotspotsDao.getHotspots()

    init {
        getHotspotDatesFromCsv()
    }

    private fun getHotspotDatesFromCsv() {
        viewModelScope.launch(Dispatchers.IO) {
            val hotspotsCSV = "csv/hotspots10k.csv"
            assetManager?.let {
                val inputStream: InputStream = assetManager.open(hotspotsCSV)
                val reader = inputStream.bufferedReader(StandardCharsets.UTF_8)
                try {
                    hotspotsDao.insertHotspots(mutableListOf<HotspotModel>().apply {
                        reader.lines().forEach { line ->
                            val tokens = line.split(",")
//                            tokens.forEach { token -> Log.e("TOKEN", token) }
                            try {
                                add(
                                    HotspotModel(
                                        tokens[1].trim().toInt(),
                                        tokens[2].trim().toDouble(),
                                        tokens[3].trim().toDouble()
                                    )
                                )
                            } catch (e: NumberFormatException) {
                                e.printStackTrace()
                            }
                        }
                    })
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

        }
    }


}
