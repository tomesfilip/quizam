package com.example.quizam_.domain.use_case

import com.example.quizam_.domain.model.User
import com.example.quizam_.domain.repository.UserRepository

class UpdateUser(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User) {
        userRepository.update(user)
    }
}