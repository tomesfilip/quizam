package com.example.quizam_.di

import com.example.quizam_.common.Constants.BASE_URL_TRIVIA
import com.example.quizam_.data.network.OpenTriviaApi
import com.example.quizam_.data.repository.QuizCategoryRepositoryImpl
import com.example.quizam_.domain.repository.CategoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideOpenTriviaApi(): OpenTriviaApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_TRIVIA)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenTriviaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(api: OpenTriviaApi): CategoryRepository {
        return QuizCategoryRepositoryImpl(api)
    }
}