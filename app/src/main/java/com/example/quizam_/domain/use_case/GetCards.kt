package com.example.quizam_.domain.use_case

import com.example.quizam_.common.Resource
import com.example.quizam_.domain.model.QuizCard
import com.example.quizam_.domain.model.QuizCategory
import com.example.quizam_.domain.repository.CardsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCards @Inject constructor(
    private val repository: CardsRepository
) {
    operator fun invoke(id: Int): Flow<Resource<List<QuizCard>>> = flow {
        try {
            val quizCards = repository.getQuizCardsByCategory(id)
            emit(Resource.Success(quizCards))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected http error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error("Connection error: check your connection."))
        }
    }
}