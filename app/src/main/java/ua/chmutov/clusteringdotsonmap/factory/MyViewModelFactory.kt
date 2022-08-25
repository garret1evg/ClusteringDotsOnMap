package ua.chmutov.clusteringdotsonmap.factory

import android.app.Application
import android.content.res.AssetManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ua.chmutov.clusteringdotsonmap.viewmodel.MapViewModel


class MyViewModelFactory(private val assetManager: AssetManager?) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MapViewModel(assetManager) as T
    }
}