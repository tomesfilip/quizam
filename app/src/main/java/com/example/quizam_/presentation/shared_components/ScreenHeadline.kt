package com.example.quizam_.presentation.shared_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.quizam_.R


@Composable
fun ScreenHeadline(
    headlineText: String
) {
    TopAppBar(
        modifier = Modifier.height(80.dp),
        title = {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.secondary),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = headlineText,
                    color = Color.Black,
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )
            }
        },
        backgroundColor = colorResource(id = R.color.bright_yellow),
        contentColor = Color.Black
    )
}
