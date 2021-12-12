package com.example.quizam_.presentation.game_result

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

@Composable
fun ResultScreen(
    navController: NavController,
    viewModel: GameResultViewModel = hiltViewModel()
) {
    val userState = viewModel.userState.value

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
            Text(
                text = "Username: ${userState.user?.userName}",
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.bright_yellow),
                style = MaterialTheme.typography.h1,
                modifier = Modifier.padding(end = 12.dp)
            )

            Text(
                text = "Score: ${userState.user?.userScore}",
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.bright_yellow),
                style = MaterialTheme.typography.h1
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