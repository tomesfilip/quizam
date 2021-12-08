package com.example.quizam_.presentation.card_list

import com.example.quizam_.domain.model.QuizCard

data class CardDetailState(
    val quizCard: QuizCard? = null,
    val error: String = ""
)