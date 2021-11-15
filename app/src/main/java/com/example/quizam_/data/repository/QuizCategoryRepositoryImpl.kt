package com.example.quizam_.data.repository

import com.example.quizam_.data.network.OpenTriviaApi
import com.example.quizam_.domain.model.QuizCategory
import com.example.quizam_.domain.repository.CategoryRepository
import javax.inject.Inject

class QuizCategoryRepositoryImpl @Inject constructor(
    private val api: OpenTriviaApi
) : CategoryRepository {
    override suspend fun getCategories(): List<QuizCategory> {
        return api.getQuizCategories().trivia_categories
    }

}