package com.example.dagger2_example.network

import com.example.dagger2_example.model.UserData
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<UserData>>
}