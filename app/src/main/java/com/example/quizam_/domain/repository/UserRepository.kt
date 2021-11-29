package com.example.quizam_.domain.repository

import com.example.quizam_.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getAllUsers(): Flow<List<User>>

    suspend fun insert(user: User)

    suspend fun delete(user: User)
}