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
import com.example.interactivemap.ui.screens.StartScreen
import 	android.media.MediaPlayer
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
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
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
                    player!!.start()
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    InteractiveMapTheme {
        //val ninthFloor: NinthFloor = viewModel() | viewModel = ninthFloor
        StartScreen()
    }
}