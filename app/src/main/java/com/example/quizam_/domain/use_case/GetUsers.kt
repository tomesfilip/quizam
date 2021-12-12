package com.example.quizam_.domain.use_case

import android.util.Log
import com.example.quizam_.domain.model.User
import com.example.quizam_.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetUsers(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Flow<List<User>> {
        Log.v("GetUsers", "invoking a get users function..")
        return userRepository.getUsers().map { users ->
            Log.v("GetUsers", "mapping user")
            users.sortedBy { it.userScore }
        }
    }
}