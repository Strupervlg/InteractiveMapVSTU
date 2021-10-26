package com.example.interactivemap.viewmodels

import android.app.Application
import android.content.Context
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.interactivemap.R
import com.example.interactivemap.providers.*
import kotlinx.coroutines.launch
import ovh.plrapps.mapcompose.api.*
import ovh.plrapps.mapcompose.ui.state.MapState

class NinthFloor(application: Application) : AndroidViewModel(application) {
    private val appContext: Context by lazy {
        getApplication<Application>().applicationContext
    }
    private val tileStreamProvider = makeTileStreamProvider9(appContext)

    private val markers = listOf(
        MarkerInfo("901", 0.518, 0.62, R.drawable.cab901),
        MarkerInfo("902а", 0.46, 0.62, R.drawable.cab902a),
        MarkerInfo("902б", 0.4, 0.62, R.drawable.cab902b),
        MarkerInfo("902в", 0.347, 0.62, R.drawable.cab902v),
        MarkerInfo("903", 0.3, 0.62, R.drawable.cab903),
        MarkerInfo("904", 0.245, 0.62, R.drawable.cab904),
        MarkerInfo("905", 0.245, 0.45, R.drawable.cab905),
        MarkerInfo("906", 0.505, 0.45, R.drawable.cab906),
        MarkerInfo("907", 0.535, 0.45, R.drawable.cab907),
        MarkerInfo("908", 0.57, 0.41, R.drawable.cab908)
    )

    val state: MapState by mutableStateOf(
        MapState(5, 4056, 2048, tileStreamProvider).apply {
            for (marker in markers) {
                addMarker(marker.id, marker.x, marker.y) {
                    Icon(
                        painter = painterResource(id = marker.idPaint),
                        contentDescription = null,
                        modifier = Modifier.size(50.dp),
                        tint = Color(0xCC2196F3)
                    )
                }
            }

            onMarkerClick { id, x, y ->

            }

            shouldLoopScale = true
            enableRotation()
            viewModelScope.launch {
                scrollTo(0.4, 0.25, 1f)
            }
        }
    )
}

private data class MarkerInfo(val id: String, val x: Double, val y: Double, val idPaint: Int)