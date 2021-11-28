package com.example.quizam_.presentation.category_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quizam_.R
import com.example.quizam_.domain.model.QuizCategory
import com.example.quizam_.presentation.util.truncateCategoryName

@Composable
fun CategoryListItem(
    quizCategory: QuizCategory,
    onItemClick: (QuizCategory) -> Unit
) {

    Box(
     modifier = Modifier
         .clickable { onItemClick(quizCategory) }
         .padding(16.dp)
         .clip(RoundedCornerShape(topStartPercent = 20, bottomEndPercent = 20))
         .background(color = colorResource(id = R.color.bright_yellow))
         .height(120.dp)
         .aspectRatio(1f),
    contentAlignment = Alignment.Center
    ) {
        Text(
            text = truncateCategoryName(quizCategory.name),
            style = MaterialTheme.typography.h2,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(4.dp)
        )
    }
}