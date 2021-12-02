package com.example.quizam_.presentation.game_start

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quizam_.R
import com.example.quizam_.presentation.Screen
import com.example.quizam_.presentation.shared_components.ScreenHeadline

@Composable
fun GameStartScreen(
    navController: NavController,
    viewModel: GameStartViewModel = hiltViewModel()
) {
    val userNameState = viewModel.userName.value
    var usrName by remember { mutableStateOf("") }
//    val scope = rememberCoroutineScope()

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
                        value = usrName,
                        onValueChange = { user ->
                            usrName = user
//                            viewModel.onEvent(GameStartEvent.EnteredUserName(it))
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
                        onClick = {
                            viewModel.onEvent(GameStartEvent.SaveUser)
                            navController.navigate(Screen.CategoryListScreen.route)
                        },
//            Modifier.background()
                    ) {
                        Icon(
                            Icons.Filled.PlayArrow,
                            contentDescription = "Play game button",
                            tint = Color.Yellow,
                            modifier = Modifier.scale(2f)
                        )
                    }
                }
            }
        }
    )


}