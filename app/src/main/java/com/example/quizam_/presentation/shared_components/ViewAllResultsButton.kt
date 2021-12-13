package com.example.quizam_.presentation.shared_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quizam_.R
import com.example.quizam_.presentation.Screen

@Composable
fun ViewAllResultsButton(navController: NavController) {
    Button(
        shape = RoundedCornerShape(40.dp),
        onClick = { navController.navigate(Screen.GameLeaderBoardScreen.route)},
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.bright_yellow)
        ),
        modifier = Modifier.padding(12.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_leaderboard_button),
            contentDescription = stringResource(id = R.string.leaderboard_button_desc),
            modifier = Modifier
                .size(32.dp)
                .padding(start = 10.dp)
        )
        Text(
            text = stringResource(id = R.string.leaderboard),
            Modifier.padding(start = 10.dp, end = 12.dp),
            style = MaterialTheme.typography.h2,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}