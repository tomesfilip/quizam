package com.example.quizam_.presentation.game_start

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quizam_.R
import com.example.quizam_.presentation.Screen
import com.example.quizam_.presentation.shared_components.ScreenHeadline
import kotlinx.coroutines.flow.collectLatest

@Composable
fun GameStartScreen(
    navController: NavController,
    viewModel: GameStartViewModel = hiltViewModel()
) {
    val userNameState = viewModel.userName.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is GameStartViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.msg
                    )
                }
                is GameStartViewModel.UiEvent.InsertUser -> {
                    navController.navigate(Screen.CategoryListScreen.route)
                }
            }
        }
    }

    Scaffold(
        topBar = {
            ScreenHeadline(headlineText = "Enter a name")
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.light_red)),
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column() {
                    OutlinedTextField(
                        textStyle = TextStyle(Color.White),
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = colorResource(id = R.color.bright_yellow),
                            unfocusedBorderColor = colorResource(id = R.color.light_yellow)
                        ),
                        value = userNameState.text,
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
                }
                Spacer(modifier = Modifier.height(20.dp))
                Column() {
                    Button(
                        shape = RoundedCornerShape(40.dp),
                        onClick = {
                            viewModel.onEvent(GameStartEvent.InsertUser)
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(id = R.color.bright_yellow)
                        ),
                        modifier = Modifier.padding(12.dp),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_play_button),
                            contentDescription = stringResource(id = R.string.play_button_desc),
                            modifier = Modifier
                                .size(32.dp)
                                .padding(start = 10.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.start_game),
                            Modifier.padding(start = 10.dp, end = 12.dp),
                            style = MaterialTheme.typography.h2
                        )
                    }
                }
            }
        }
    )
}