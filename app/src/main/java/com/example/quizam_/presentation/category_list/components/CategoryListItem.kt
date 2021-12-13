package com.example.quizam_.presentation.category_list.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.quizam_.domain.model.QuizCategory
import com.example.quizam_.presentation.util.truncateCategoryName

@Composable
fun CategoryListItem(
    quizCategory: QuizCategory,
    onItemClick: (QuizCategory) -> Unit
) {

    Button(
        onClick = { onItemClick(quizCategory) },
        shape = RoundedCornerShape(topStartPercent = 20, bottomEndPercent = 20),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow),
        contentPadding = PaddingValues(4.dp),
        modifier = Modifier
            .padding(12.dp)
            .aspectRatio(1f)

    ) {
        Text(
            text = truncateCategoryName(quizCategory.name),
            style = MaterialTheme.typography.h2,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}