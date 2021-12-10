package com.example.interactivemap

import android.media.AudioAttributes
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.interactivemap.ui.theme.InteractiveMapTheme
import 	android.media.MediaPlayer
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.interactivemap.ui.screens.*
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    @ExperimentalComposeUiApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        val player = MediaPlayer.create(this, R.raw.cut_map)

        super.onCreate(savedInstanceState)
        setContent {
            InteractiveMapTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    //val ninthFloor: NinthFloor = viewModel() | viewModel = ninthFloor
                    StartScreen()

                    player!!.start()
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    InteractiveMapTheme {
        //val ninthFloor: NinthFloor = viewModel() | viewModel = ninthFloor
        StartScreen()
    }
}