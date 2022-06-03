package com.example.dagger2_example.utils

import com.example.dagger2_example.database.entity.UserEntity

sealed class UserResource {
    object Loading : UserResource()
    data class Success(val list: List<UserEntity>) : UserResource()
    data class Error(val message: String) : UserResource()
}