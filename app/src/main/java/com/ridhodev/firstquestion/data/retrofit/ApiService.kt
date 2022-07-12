package com.ridhodev.firstquestion.data.retrofit

import com.ridhodev.firstquestion.data.response.UserResponse
import retrofit2.http.*

interface ApiService {
    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): UserResponse
}

