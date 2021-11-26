package com.example.quizam_.data.repository

import com.example.quizam_.data.network.OpenTriviaApi
import com.example.quizam_.data.network.dto.QuizCategoryListDto
import com.example.quizam_.domain.repository.CategoryRepository
import javax.inject.Inject

class QuizCategoryRepositoryImpl @Inject constructor(
    private val api: OpenTriviaApi
) : CategoryRepository {

    override suspend fun getCategories(): QuizCategoryListDto {
        return api.getQuizCategories()
    }
}