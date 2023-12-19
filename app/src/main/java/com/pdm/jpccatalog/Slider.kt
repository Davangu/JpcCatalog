package com.pdm.jpccatalog

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextRange


@Composable
fun BasicSlider() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        var sliderPosition by rememberSaveable {
            mutableStateOf(0f)
        }
        Slider(value = sliderPosition, onValueChange = { sliderPosition = it })
        Text(text = sliderPosition.toString())
    }
}

@Composable
fun AdvSlider() {
    var sliderPosition by rememberSaveable {
        mutableStateOf(0f)
    }
    var openAlert by rememberSaveable {
        mutableStateOf(false)
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it
                if (sliderPosition == 100f) openAlert = true},
            valueRange = 0f..100f,
            steps = 9
        )
        Text(text = "%.0f".format(sliderPosition))
    }
    if(openAlert) {
        MyAlertDialog(
            onConfirm = { sliderPosition = 80f
                        openAlert = false},
            onDismiss = { openAlert = false }
        )
    }
}