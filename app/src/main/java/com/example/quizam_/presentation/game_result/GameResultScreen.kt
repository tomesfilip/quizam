package com.example.quizam_.presentation.game_result

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quizam_.R
import com.example.quizam_.presentation.Screen
import com.example.quizam_.presentation.shared_components.RestartGameButton
import com.example.quizam_.presentation.shared_components.ViewAllResultsButton

@Composable
fun ResultScreen(
    navController: NavController,
    viewModel: GameResultViewModel = hiltViewModel()
) {
    val userState = viewModel.userState.value

    BackHandler() {
        navController.navigate(Screen.GameStartScreen.route)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.light_red)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
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

        Column {
            RestartGameButton(navController = navController)
            ViewAllResultsButton(navController = navController)
        }

    }
}