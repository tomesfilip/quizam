package com.example.quizam_.data.network.dto

import com.example.quizam_.domain.model.QuizCard

data class QuizCardListDto(
    val response_code: Int,
    val results: List<QuizCard>
)