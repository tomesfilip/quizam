package com.example.quizam_.presentation.card_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun QuizCardOption (
    option: String,
    onSelectOptionClick: () -> Unit
) {
    Button(
        onClick = onSelectOptionClick,
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = Color.Yellow
        ),
        shape = RoundedCornerShape(topStartPercent = 50, bottomEndPercent = 50),
        modifier = Modifier.padding(10.dp).fillMaxWidth()
    ) {
        Text(
            text = option,
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2
        )
    }
}