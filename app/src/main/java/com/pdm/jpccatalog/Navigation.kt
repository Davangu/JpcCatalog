package com.pdm.jpccatalog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.pdm.jpccatalog.model.Routes
import com.pdm.jpccatalog.model.Routes.*

@Composable
fun Screen1(navigationController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        var loading = true
        var myProgress = 0.0f
        if (loading) CircularProgressIndicator(progress = myProgress)

        LinearProgressIndicator()
        Text(
            text = "screen1",
            modifier = Modifier
                .align(Alignment.Center)
                .clickable { navigationController.navigate(Pantalla2.route) })
    }
}

@Composable
fun Screen2(navigationController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        Text(text = "screen2", modifier = Modifier
            .align(Alignment.Center)
            .clickable { navigationController.navigate(Pantalla3.route) })
    }
}

@Composable
fun Screen3(navigationController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Text(text = "screen3", modifier = Modifier
            .align(Alignment.Center)
            .clickable { navigationController.navigate(Pantalla4.createRoute("David")) })
    }
}

@Composable
fun Screen4(navigationController: NavHostController, name: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
    ) {
        Text(text = name, modifier = Modifier
            .align(Alignment.Center)
            .clickable { navigationController.navigate(Pantalla5.createRoute("David Antolín")) })
    }
}

@Composable
fun Screen5(navigationController: NavHostController, name: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        Text(text = "Me llamo $name", modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .clickable {
                navigationController.navigate(Pantalla1.route)
            })
    }
}