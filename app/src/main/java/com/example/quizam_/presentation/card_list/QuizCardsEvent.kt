package com.example.quizam_.presentation.card_list


sealed class QuizCardsEvent {
    data class ClickedOption(val option_answer: String): QuizCardsEvent()
}
