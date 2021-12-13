package com.example.quizam_.domain.use_case

import com.example.quizam_.common.Resource
import com.example.quizam_.data.network.dto.toQuizCategory
import com.example.quizam_.domain.model.QuizCategory
import com.example.quizam_.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCategories @Inject constructor(
    private val repository: CategoryRepository
) {
    operator fun invoke(): Flow<Resource<List<QuizCategory>>> = flow {
        try {
            emit(Resource.Loading())
            val categories =
                repository.getCategories().trivia_categories.map { it.toQuizCategory() }
            emit(Resource.Success(categories))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "Unexpected http error occurred."
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error("Connection error: check your connection."))
        }
    }
}