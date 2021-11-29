package com.example.quizam_.domain.use_case

data class UserUseCases(
    val getUsers: GetUsers,
    val insertUser: InsertUser,
    val deleteUser: DeleteUser
)