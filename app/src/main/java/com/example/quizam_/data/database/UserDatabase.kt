package com.example.quizam_.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.quizam_.domain.model.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {
    abstract val userDao: UserDao

    companion object {
        const val DATABASE_NAME = "user_database"
    }
}