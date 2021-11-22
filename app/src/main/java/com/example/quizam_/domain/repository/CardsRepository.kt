package com.example.quizam_.domain.repository

import com.example.quizam_.domain.model.QuizCard

interface CardsRepository {
    suspend fun getQuizCardsByCategory(id: Int): List<QuizCard>
}