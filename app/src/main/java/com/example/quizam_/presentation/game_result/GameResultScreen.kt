package com.example.quizam_.presentation.game_result

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quizam_.R
import com.example.quizam_.presentation.Screen
import com.example.quizam_.presentation.game_start.GameStartEvent

@Composable
fun ResultScreen(
    navController: NavController,
    viewModel: GameResultViewModel = hiltViewModel()
) {
    val usersState = viewModel.usersState.value
    Log.v("GameResultScreen", "usersState: ${usersState.users}")

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
                text = "Username: ${usersState.users[0].userName}",
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.bright_yellow),
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(end = 12.dp)
            )

        }
        Column() {
            // TODO: result score
            Text(
                text = "Score: ${usersState.users[0].userScore}",
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.bright_yellow),
                style = MaterialTheme.typography.h3
            )
        }
        Column() {
            Button(
                shape = RoundedCornerShape(40.dp),
                onClick = { navController.navigate(Screen.GameStartScreen.route)},
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.bright_yellow)),
                modifier = Modifier.padding(12.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_restart_button),
                    contentDescription = stringResource(id = R.string.restart_button_desc),
                    modifier = Modifier
                        .size(32.dp)
                        .padding(start = 10.dp)
                )
                Text(
                    text = stringResource(id = R.string.restart_game),
                    Modifier.padding(start = 10.dp, end = 12.dp),
                    style = MaterialTheme.typography.h2,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}