package com.example.dagger2_example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dagger2_example.database.entity.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(list: List<UserEntity>)

    @Query("select * from users_table")
    suspend fun getUsers(): List<UserEntity>
}