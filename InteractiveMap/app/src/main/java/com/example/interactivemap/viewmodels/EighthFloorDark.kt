package com.example.interactivemap.viewmodels

import android.app.Application
import android.content.Context
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.interactivemap.R
import com.example.interactivemap.providers.*
import com.example.interactivemap.ui.theme.DarkPrimary
import kotlinx.coroutines.launch
import ovh.plrapps.mapcompose.api.*
import ovh.plrapps.mapcompose.ui.layout.Fill
import ovh.plrapps.mapcompose.ui.state.MapState

class EighthFloorDark(application: Application) : AndroidViewModel(application) {

    private val appContext: Context by lazy {
        getApplication<Application>().applicationContext
    }

    private var tileStreamProvider = makeTileStreamProvider8Dark(appContext)

    private val markers = listOf(
        MarkerInfo("801", 0.5476, 0.59, R.drawable.cab801),
        MarkerInfo("802", 0.4, 0.59, R.drawable.cab802),
        MarkerInfo("803", 0.3, 0.59, R.drawable.cab803),
        MarkerInfo("804", 0.245, 0.58, R.drawable.cab804),
        MarkerInfo("805", 0.245, 0.41, R.drawable.cab805),
        MarkerInfo("806", 0.52, 0.40, R.drawable.cab806),
        MarkerInfo("807", 0.573, 0.37, R.drawable.cab807)
    )

    val state: MapState by mutableStateOf(
        MapState(5, 4056, 2048, tileStreamProvider).apply {
            minimumScaleMode = Fill
            for (marker in markers) {
                addMarker(marker.id, marker.x, marker.y) {
                    Icon(
                        painter = painterResource(id = marker.idPaint),
                        contentDescription = null,
                        modifier = Modifier.size(35.dp),
                        tint = DarkPrimary
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

    fun onCenter(id : String) {
        viewModelScope.launch {
            state.centerOnMarker(id, 5f)
        }
    }
}
