package com.example.quizam_.domain.repository

import com.example.quizam_.data.network.dto.QuizCard

interface CardRepository {
    suspend fun getQuizCards(): List<QuizCard>
}