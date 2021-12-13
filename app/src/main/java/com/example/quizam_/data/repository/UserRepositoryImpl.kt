package com.example.quizam_.data.repository

import com.example.quizam_.domain.model.User
import com.example.quizam_.data.database.UserDao
import com.example.quizam_.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {
    override fun getUsers(): Flow<List<User>> {
        return userDao.getUsers()
    }

    override fun getLastUser(): Flow<User> {
        return userDao.getLastUser()
    }

    override suspend fun insert(user: User) {
        return userDao.insert(user)
    }

    override suspend fun update(user: User) {
        return userDao.update(user)
    }

    override suspend fun delete(user: User) {
        return userDao.delete(user)
    }

    override suspend fun deleteUsers() {
        return userDao.deleteUsers()
    }

}