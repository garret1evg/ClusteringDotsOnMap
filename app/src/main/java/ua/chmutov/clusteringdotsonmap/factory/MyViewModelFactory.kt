package ua.chmutov.clusteringdotsonmap.factory

import android.content.res.AssetManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ua.chmutov.clusteringdotsonmap.db.dao.HotspotsDao
import ua.chmutov.clusteringdotsonmap.viewmodel.MapViewModel


class MyViewModelFactory(private val assetManager: AssetManager, private val hotspotsDao: HotspotsDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MapViewModel(assetManager,hotspotsDao) as T
    }
}