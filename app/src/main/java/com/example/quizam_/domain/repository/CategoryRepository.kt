package com.example.quizam_.domain.repository

import com.example.quizam_.data.network.dto.QuizCategoryListDto

interface CategoryRepository {
    suspend fun getCategories(): QuizCategoryListDto
}