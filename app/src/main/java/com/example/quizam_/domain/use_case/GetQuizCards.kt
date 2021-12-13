package com.example.quizam_.domain.use_case

import com.example.quizam_.common.Resource
import com.example.quizam_.data.network.dto.toQuizCard
import com.example.quizam_.domain.model.QuizCard
import com.example.quizam_.domain.repository.QuizCardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetQuizCards @Inject constructor(
    private val repository: QuizCardRepository
) {
    operator fun invoke(category: Int): Flow<Resource<List<QuizCard>>> = flow {
        try {
            emit(Resource.Loading())
            val quizCards =
                repository.getQuizCardsByCategory(category).results.map { it.toQuizCard() }
            emit(Resource.Success(quizCards))
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