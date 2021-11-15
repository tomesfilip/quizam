package com.example.quizam_.domain.repository

import com.example.quizam_.domain.model.QuizCategory

interface CategoryRepository {
    suspend fun getCategories(): List<QuizCategory>
}