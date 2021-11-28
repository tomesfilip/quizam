package com.example.quizam_.presentation.card_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CardListCategoryBottom(
    categoryName: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
//            .padding(10.dp)
//            .clip(RoundedCornerShape(topStartPercent = 50, topEndPercent = 50))
            .background(MaterialTheme.colors.secondary)

    ) {
        Text(
            text = categoryName,
            style = MaterialTheme.typography.h3,
            textAlign = TextAlign.Center
        )
    }
}