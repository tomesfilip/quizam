package com.example.quizam_.presentation.category_list

import com.example.quizam_.domain.model.QuizCategory

data class CategoryListState(
    val isLoading: Boolean = false,
    val categories: List<QuizCategory> = emptyList(),
    val error: String = ""
)
