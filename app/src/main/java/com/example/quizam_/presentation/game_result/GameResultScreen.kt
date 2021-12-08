package com.example.quizam_.presentation.game_result

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.quizam_.R
import com.example.quizam_.presentation.Screen

@Composable
fun ResultScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.light_red)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // TODO: username
            Text(
                text = "Username",
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.bright_yellow),
                style = MaterialTheme.typography.h3
            )
        }
        Column() {
            // TODO: result score
            Text(
                text = "Score",
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.bright_yellow),
                style = MaterialTheme.typography.h3
            )
        }
        Column() {
            Button(
                onClick = { navController.navigate(Screen.GameStartScreen.route)},

            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_restart_button),
                    contentDescription = "restart game",
                )
            }
        }
    }
}