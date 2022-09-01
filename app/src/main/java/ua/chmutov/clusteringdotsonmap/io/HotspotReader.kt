package ua.chmutov.clusteringdotsonmap.io

import ua.chmutov.clusteringdotsonmap.model.HotspotData
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets

class HotspotReader {

    @Throws(IOException::class)
    fun read(inputStream: InputStream): List<HotspotData> {
        return mutableListOf<HotspotData>().apply {
            inputStream.bufferedReader(StandardCharsets.UTF_8).lines().forEach { line ->
                val tokens = line.split(",")
//                            tokens.forEach { token -> Log.e("TOKEN", token) }
                try {
                    add(
                        HotspotData(
                            tokens[1].trim().toInt(),
                            tokens[2].trim().toDouble(),
                            tokens[3].trim().toDouble()
                        )
                    )
                } catch (e: NumberFormatException) {
                    e.printStackTrace()
                }
            }
        }
    }
}