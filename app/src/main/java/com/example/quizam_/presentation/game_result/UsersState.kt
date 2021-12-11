package com.example.quizam_.presentation.game_result

import com.example.quizam_.domain.model.User

data class UsersState(
    val users: List<User> = emptyList()
)
