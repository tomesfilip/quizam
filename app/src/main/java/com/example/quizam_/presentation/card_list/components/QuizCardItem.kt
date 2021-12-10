package com.example.quizam_.presentation.card_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.quizam_.R
import com.example.quizam_.domain.model.QuizCard
import com.example.quizam_.presentation.Screen


@Composable
fun QuizCardItem(
    quizCard: QuizCard,
    onSelectedOptionClick: () -> Unit
) {
    Column(
    modifier = Modifier
    .fillMaxWidth(),
    ) {
        Text(
            text = quizCard.question,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1,
            color = Color.White
        )
        Spacer(modifier = Modifier.size(20.dp))
        quizCard.options.forEach { option ->
            QuizCardOption(option = option, onSelectOptionClick = onSelectedOptionClick)
        }
    }
}
