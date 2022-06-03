package com.example.dagger2_example.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
class UserEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
)