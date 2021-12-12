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

class SixthFloorDark(application: Application) : AndroidViewModel(application) {
    private val appContext: Context by lazy {
        getApplication<Application>().applicationContext
    }

    private var tileStreamProvider = makeTileStreamProvider6Dark(appContext)

    private val markers = listOf(
        MarkerInfo("601", 0.5765, 0.59, R.drawable.cab601),
        MarkerInfo("602", 0.518, 0.59, R.drawable.cab602),
        MarkerInfo("603", 0.46, 0.59, R.drawable.cab603),
        MarkerInfo("604", 0.4, 0.59, R.drawable.cab604),
        MarkerInfo("605", 0.345, 0.59, R.drawable.cab605),
        MarkerInfo("606", 0.3, 0.59, R.drawable.cab606),
        MarkerInfo("607", 0.245, 0.58, R.drawable.cab607),
        MarkerInfo("608", 0.245, 0.41, R.drawable.cab608),
        MarkerInfo("609", 0.288, 0.41, R.drawable.cab609),
        MarkerInfo("610", 0.505, 0.40, R.drawable.cab610),
        MarkerInfo("611", 0.535, 0.40, R.drawable.cab611),
        MarkerInfo("612", 0.57, 0.37, R.drawable.cab612)
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
