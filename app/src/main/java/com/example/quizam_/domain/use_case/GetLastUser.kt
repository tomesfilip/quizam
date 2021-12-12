package com.example.quizam_.domain.use_case

import com.example.quizam_.domain.model.User
import com.example.quizam_.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetLastUser(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Flow<User> {
        return userRepository.getLastUser()
    }
}