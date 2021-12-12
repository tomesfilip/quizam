package com.example.quizam_.di

import android.app.Application
import androidx.room.Room
import com.example.quizam_.common.Constants.BASE_URL_TRIVIA
import com.example.quizam_.data.database.UserDatabase
import com.example.quizam_.data.network.OpenTriviaApi
import com.example.quizam_.data.repository.QuizCardRepositoryImpl
import com.example.quizam_.data.repository.QuizCategoryRepositoryImpl
import com.example.quizam_.data.repository.UserRepositoryImpl
import com.example.quizam_.domain.repository.CategoryRepository
import com.example.quizam_.domain.repository.QuizCardRepository
import com.example.quizam_.domain.repository.UserRepository
import com.example.quizam_.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Provides
    @Singleton
    fun provideQuizCardRepository(api: OpenTriviaApi): QuizCardRepository {
        return QuizCardRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideUserDatabase(application: Application): UserDatabase {
        return Room.databaseBuilder(
            application,
            UserDatabase::class.java,
            UserDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserRepository(database: UserDatabase): UserRepository {
        return UserRepositoryImpl(database.userDao)
    }

    @Provides
    @Singleton
    fun provideUserUseCases(userRepository: UserRepository): UserUseCases {
        return UserUseCases(
            getUsers = GetUsers(userRepository),
            getLastUser = GetLastUser(userRepository),
            insertUser = InsertUser(userRepository),
            updateUser = UpdateUser(userRepository),
            deleteUser = DeleteUser(userRepository)
        )
    }
}