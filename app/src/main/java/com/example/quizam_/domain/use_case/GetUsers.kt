package com.example.quizam_.domain.use_case

import com.example.quizam_.domain.model.User
import com.example.quizam_.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetUsers(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Flow<List<User>> {
        return userRepository.getUsers().map { users ->
            users.sortedBy { it.userScore }
        }
    }
}