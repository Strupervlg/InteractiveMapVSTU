package com.example.interactivemap.ui.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.interactivemap.ui.theme.InteractiveMapTheme
import com.example.interactivemap.viewmodels.*
import ovh.plrapps.mapcompose.ui.MapUI
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

var sizeSpaceBetweenButtons : Float = 1.5F

@Composable
fun StartScreen(modifier: Modifier = Modifier) {
    val floors = listOf(14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
    val selectedOption = remember { mutableStateOf(floors[0]) }

    if(selectedOption.value == 9) {
        val floor: NinthFloor = viewModel()
        MapUI(modifier, state = floor.state)
    }
    if(selectedOption.value == 6) {
        val floor: SixthFloor = viewModel()
        MapUI(modifier, state = floor.state)
    }

    Column(modifier =Modifier.padding(10.dp)) {
        floors.forEach { floor ->
            val selected = selectedOption.value == floor
            Button(onClick = { selectedOption.value = floor }, modifier =
                Modifier
                    .selectable(
                        selected = selected,
                        onClick = {  }
                    )
                    .border(
                        width= if(selected){2.dp} else{0.dp},
                        color = Color.Black
                    ),
                colors = ButtonDefaults.textButtonColors(
                backgroundColor = if(selected){
                    MaterialTheme.colors.secondary}else{
                    MaterialTheme.colors.primary},
                contentColor = Color.Black
            )
            ) {
                Text(text = floor.toString())
            }
            Spacer(modifier = Modifier.height(sizeSpaceBetweenButtons.dp))
        }
    }


}
