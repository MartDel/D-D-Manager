package com.example.projet_android.api.dto

data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)

data class LoginResponse(
    val jwt_token: String
)

data class RegisterResponse(
    val username: String,
    val email: String,
)