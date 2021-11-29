package com.example.quizam_.data.repository

import com.example.quizam_.domain.model.User
import com.example.quizam_.data.database.UserDao
import com.example.quizam_.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override fun getAllUsers(): Flow<List<User>> {
        return userDao.getAllUsers()
    }

    override suspend fun insert(user: User) {
        return userDao.insert(user)
    }

    override suspend fun delete(user: User) {
        return userDao.delete(user)
    }

}