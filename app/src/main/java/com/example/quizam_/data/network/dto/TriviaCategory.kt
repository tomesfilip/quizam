package com.example.quizam_.data.network.dto

import com.example.quizam_.domain.model.QuizCategory

data class TriviaCategory(
    val id: Int,
    val name: String
)


fun TriviaCategory.toQuizCategory(): QuizCategory {
    return QuizCategory(
        id = id,
        name = name
    )
}
