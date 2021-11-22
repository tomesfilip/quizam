package com.example.quizam_.data.repository

import com.example.quizam_.data.network.OpenTriviaApi
import com.example.quizam_.data.network.dto.QuizCard
import com.example.quizam_.domain.repository.CardsRepository
import javax.inject.Inject

class QuizCardsRepositoryImpl @Inject constructor(
    private val api: OpenTriviaApi
) :  CardsRepository {

    override suspend fun getQuizCardsByCategory(id: Int): List<QuizCard> {
        return api.getQuizCardsByCategory(id).results
    }
}