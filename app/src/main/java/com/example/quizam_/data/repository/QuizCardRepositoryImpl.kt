package com.example.quizam_.data.repository

import com.example.quizam_.data.network.OpenTriviaApi
import com.example.quizam_.data.network.dto.QuizCardListDto
import com.example.quizam_.domain.repository.QuizCardRepository
import javax.inject.Inject

class QuizCardRepositoryImpl @Inject constructor(
    private val api: OpenTriviaApi
) :  QuizCardRepository {

    override suspend fun getQuizCardsByCategory(id: Int): QuizCardListDto {
        return api.getQuizCardsByCategory(id)
    }
}