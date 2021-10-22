package com.example.interactivemap.viewmodels

import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.interactivemap.providers.makeTileStreamProvider
import kotlinx.coroutines.launch
import ovh.plrapps.mapcompose.api.enableRotation
import ovh.plrapps.mapcompose.api.scrollTo
import ovh.plrapps.mapcompose.api.shouldLoopScale
import ovh.plrapps.mapcompose.ui.state.MapState

class NinthFloor(application: Application) : AndroidViewModel(application) {
    private val appContext: Context by lazy {
        getApplication<Application>().applicationContext
    }
    private val tileStreamProvider = makeTileStreamProvider(appContext)

    val state: MapState by mutableStateOf(
        MapState(3, 904, 416, tileStreamProvider).apply {
            shouldLoopScale = true
            enableRotation()
            viewModelScope.launch {
                scrollTo(0.5, 0.5, 1f)
            }
        }
    )
}