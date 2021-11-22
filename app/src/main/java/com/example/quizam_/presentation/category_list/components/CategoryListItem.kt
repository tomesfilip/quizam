package com.example.quizam_.presentation.category_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quizam_.domain.model.QuizCategory

@Composable
fun CategoryListItem(
    quizCategory: QuizCategory,
    onItemClick: (QuizCategory) -> Unit
) {
    Row(
     modifier = Modifier
         .fillMaxWidth()
         .clickable { onItemClick(quizCategory) }
         .padding(20.dp),
    horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "${quizCategory.name}",
            style = MaterialTheme.typography.h2
        )
    }
}