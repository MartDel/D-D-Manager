package com.example.projet_android.repositories

import com.example.projet_android.api.AuthApiService
import com.example.projet_android.api.dto.LoginRequest
import com.example.projet_android.api.dto.RegisterRequest
import javax.inject.Inject

class AuthRepository @Inject constructor(private val authApiService: AuthApiService) {

    suspend fun login(email: String, password: String): String {
        val request = LoginRequest(email, password)
        val response = authApiService.login(request)
        if (response.isSuccessful) {
            return response.body()?.jwt_token ?: throw Exception("Token is null")
        } else {
            throw Exception("Login failed: ${response.errorBody()?.string()}")
        }
    }

    suspend fun register(username: String, email: String, password: String) {
        val request = RegisterRequest(username, email, password)
        val response = authApiService.register(request)
        if (!response.isSuccessful) {
            throw Exception("Register failed: ${response.errorBody()?.string()}")
        }
    }
}
