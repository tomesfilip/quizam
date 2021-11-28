package com.example.quizam_.domain.repository

import com.example.quizam_.data.network.dto.QuizCardListDto

interface QuizCardRepository {
    suspend fun getQuizCardsByCategory(category: Int): QuizCardListDto
}