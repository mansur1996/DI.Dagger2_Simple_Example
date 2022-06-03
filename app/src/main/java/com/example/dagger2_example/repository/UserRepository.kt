package com.example.dagger2_example.repository

import com.example.dagger2_example.database.dao.UserDao
import com.example.dagger2_example.database.entity.UserEntity
import com.example.dagger2_example.network.ApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao
) {
    suspend fun getUsers() = flow { emit(apiService.getUsers()) }

    suspend fun insertUsersToDB(list: List<UserEntity>) = userDao.addAll(list)
    suspend fun getUsersFromDB() = userDao.getUsers()
}