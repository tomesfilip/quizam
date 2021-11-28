package com.example.quizam_.data.network

import com.example.quizam_.data.network.dto.QuizCardListDto
import com.example.quizam_.data.network.dto.QuizCategoryListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenTriviaApi {

    @GET("api_category.php")
    suspend fun getQuizCategories(): QuizCategoryListDto

    @GET("api.php?amount=20&type=multiple")
    suspend fun getQuizCardsByCategory(
        @Query("category") category: Int
    ): QuizCardListDto

}