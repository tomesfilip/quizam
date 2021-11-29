package com.example.quizam_.data.database

import androidx.room.*
import com.example.quizam_.domain.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * from user ORDER BY user_score DESC")
    fun getAllUsers(): Flow<List<User>>

    // for future usage (selecting user's detail)
//    @Query("SELECT * from user WHERE id = :id")
//    fun getUserById(id: Int): Flow<User>

    // when user adds already existing name (leads to a conflict with duplicate)
    // the row will be replaced by the newer one
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

//    @Update
//    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)
}