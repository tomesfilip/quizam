package com.example.quizam_.data.network.dto

import com.example.quizam_.domain.model.QuizCard
import com.example.quizam_.domain.model.QuizCategory

data class QuizCard(
    val category: String,
    val correct_answer: String,
    val difficulty: String,
    val incorrect_answers: List<String>,
    val question: String,
    val type: String
)

fun QuizCard.toQuizCard(): QuizCard {
    return QuizCard(
        category = category,
        correct_answer = correct_answer,
        difficulty = difficulty,
        incorrect_answers = incorrect_answers,
        question = question,
        type = type,
        options = (incorrect_answers + correct_answer).shuffled()
    )
}