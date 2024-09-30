package com.example.projet_android.api

import com.example.projet_android.api.dto.LoginRequest
import com.example.projet_android.api.dto.LoginResponse
import com.example.projet_android.api.dto.RegisterRequest
import com.example.projet_android.api.dto.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>
}
