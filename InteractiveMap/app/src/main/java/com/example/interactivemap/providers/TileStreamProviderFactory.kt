package com.example.interactivemap.providers

import android.content.Context
import ovh.plrapps.mapcompose.core.TileStreamProvider

fun makeTileStreamProvider(appContext: Context) =
    TileStreamProvider { row, col, zoomLvl ->
        try {
            appContext.assets?.open("tiles/ninth_floor/$zoomLvl/$row/$col.jpg")
        } catch (e: Exception) {
            null
        }
    }