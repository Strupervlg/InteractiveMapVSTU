package com.example.interactivemap.providers

import android.content.Context
import ovh.plrapps.mapcompose.core.TileStreamProvider

fun makeTileStreamProvider6(appContext: Context) =
    TileStreamProvider { row, col, zoomLvl ->
        try {
            appContext.assets?.open("tiles/sixth_floor/$zoomLvl/$row/$col.jpg")
        } catch (e: Exception) {
            null
        }
    }

fun makeTileStreamProvider9(appContext: Context) =
    TileStreamProvider { row, col, zoomLvl ->
        try {
            appContext.assets?.open("tiles/ninth_floor/$zoomLvl/$row/$col.jpg")
        } catch (e: Exception) {
            null
        }
    }

fun makeTileStreamProvider8(appContext: Context) =
    TileStreamProvider { row, col, zoomLvl ->
        try {
            appContext.assets?.open("tiles/eighth_floor/$zoomLvl/$row/$col.jpg")
        } catch (e: Exception) {
            null
        }
    }