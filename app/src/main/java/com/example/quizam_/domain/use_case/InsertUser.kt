package com.example.quizam_.domain.use_case

import com.example.quizam_.domain.model.User
import com.example.quizam_.domain.repository.UserRepository

class InsertUser(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User) {
        if (user.userName.isBlank()) {
            throw Exception("Username field cannot be empty")
        }
        userRepository.insert(user)
    }
}