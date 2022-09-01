package ua.chmutov.clusteringdotsonmap.db.mapper

import ua.chmutov.clusteringdotsonmap.db.model.HotspotModel
import ua.chmutov.clusteringdotsonmap.model.HotspotData

fun HotspotModel.toHotspotData() = HotspotData(id, lat, lng)