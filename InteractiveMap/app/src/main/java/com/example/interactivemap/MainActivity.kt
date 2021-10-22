package com.example.interactivemap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.interactivemap.ui.theme.InteractiveMapTheme
import com.example.interactivemap.ui.screens.StartScreen
import com.example.interactivemap.viewmodels.NinthFloor


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InteractiveMapTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val ninthFloor: NinthFloor = viewModel()
                    StartScreen(viewModel = ninthFloor)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    InteractiveMapTheme {
        val ninthFloor: NinthFloor = viewModel()
        StartScreen(viewModel = ninthFloor)
    }
}