package com.example.quizam_.presentation.card_list

import com.example.quizam_.domain.model.QuizCard

data class CardListState(
    val isLoading: Boolean = false,
    val cards: List<QuizCard> = emptyList(),
    val error: String = ""
)
