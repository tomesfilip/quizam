package com.example.quizam_.data.network.dto

import com.example.quizam_.domain.model.QuizCategory

data class QuizCategory(
    val id: Int,
    val name: String
)

fun QuizCategory.toQuizCategory(): QuizCategory {
    return QuizCategory(
        id = id,
        name = name
    )
}
