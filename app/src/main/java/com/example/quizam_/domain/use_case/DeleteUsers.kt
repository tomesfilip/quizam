package com.example.quizam_.domain.use_case

import com.example.quizam_.domain.repository.UserRepository

class DeleteUsers(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() {
        return userRepository.deleteUsers()
    }
}