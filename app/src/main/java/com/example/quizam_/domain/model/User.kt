package com.example.quizam_.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userName: String,
    @ColumnInfo(name = "user_score") var userScore: Int,
) {
}