package com.example.projet_android.api.dto

import com.example.projet_android.model.Item

data class UpdatePlayerRequest(
    val firstname: String
)

data class PlayerResponse(
    val id: String,
    val roleMaster: Boolean,
    val status: String,
    val firstname: String,
    val user: UserResponse
)

data class AddedPlayerResponse(
    val id: String,
    val roleMaster: Boolean,
    val status: String,
    val firstname: String,
)

data class UserResponse(
    val id: String,
    val username: String,
    val email: String
)

data class InventoryResponse(
    val id: String,
    val item: Item
)

data class CreateInventoryResponse(
    val id: String
)

data class DeleteResponse(
    val affected: Int
)