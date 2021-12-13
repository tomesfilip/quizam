package com.example.quizam_.domain.repository

import com.example.quizam_.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers(): Flow<List<User>>

    fun getLastUser(): Flow<User>

    suspend fun insert(user: User)

    suspend fun update(user: User)

    suspend fun delete(user: User)

    suspend fun deleteUsers()
}