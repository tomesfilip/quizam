package com.example.quizam_.presentation.game_leaderboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import com.example.quizam_.presentation.shared_components.ClearLeaderBoardButton
import com.example.quizam_.presentation.shared_components.RestartGameButton
import com.example.quizam_.presentation.shared_components.ScreenHeadline

@Composable
fun GameLeaderBoardScreen(
    navController: NavController,
    viewModel: GameLeaderBoardViewModel = hiltViewModel()
) {
    val usersState = viewModel.usersState.value

    Scaffold(
        topBar = {
            ScreenHeadline(headlineText = "Leaderboard")
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.light_red))
            ) {
                LeaderBoardList(usersState)
            }
        },
        bottomBar = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                RestartGameButton(navController = navController)
                Button(
                    shape = RoundedCornerShape(40.dp),
                    onClick = { viewModel.onEvent(event = GameLeaderBoardEvent.DeleteUsers) },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.bright_yellow)
                    ),
                    modifier = Modifier.padding(12.dp),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_clear_button),
                        contentDescription = stringResource(id = R.string.clear_button_desc),
                        modifier = Modifier
                            .size(32.dp)
                            .padding(start = 10.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.clear_leaderboard),
                        Modifier.padding(start = 10.dp, end = 12.dp),
                        style = MaterialTheme.typography.h2,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

    )
}

@Composable
fun LeaderBoardList(usersState: UsersState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.75f)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(0.75f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Username",
                style = MaterialTheme.typography.h2,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Score",
                style = MaterialTheme.typography.h2,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(usersState.users) { user ->
                Row(
                    modifier = Modifier.fillMaxWidth(0.75f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${user.userName}",
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.bright_yellow),
                        style = MaterialTheme.typography.h3,
                    )

                    Text(
                        text = "${user.userScore}",
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.bright_yellow),
                        style = MaterialTheme.typography.h3
                    )
                }
            }
        }
    }
}