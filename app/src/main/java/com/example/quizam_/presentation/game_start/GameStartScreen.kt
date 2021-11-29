package com.example.quizam_.presentation.game_start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quizam_.R
import com.example.quizam_.presentation.Screen

@Composable
fun GameStartScreen(
    navController: NavController,
    viewModel: GameStartViewModel = hiltViewModel()
) {
    val userNameState = viewModel.userName.value
//    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.light_red)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        OutlinedTextField(
            textStyle = TextStyle(Color.White),
            singleLine = true,
            value = userNameState,
            onValueChange = {
                viewModel.onEvent(GameStartEvent.EnteredUserName(it))
            },
            label = {
                Text(
                    text = "Enter your name",
                    color = Color.White
                )
            }
        )
        Button(
            onClick = {
                viewModel.onEvent(GameStartEvent.SaveUser)
                navController.navigate(Screen.CategoryListScreen.route)
            },
            Modifier.background(colorResource(id = R.color.bright_yellow)),

            ) {
            Text(
                text = "START QUIZ",
                style = MaterialTheme.typography.h2
            )
        }
    }
}