package com.example.interactivemap.ui.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.interactivemap.ui.theme.InteractiveMapTheme
import com.example.interactivemap.viewmodels.NinthFloor
import ovh.plrapps.mapcompose.ui.MapUI


@Composable
fun StartScreen(modifier: Modifier = Modifier, viewModel: NinthFloor) {
    MapUI(modifier, state = viewModel.state)
}

