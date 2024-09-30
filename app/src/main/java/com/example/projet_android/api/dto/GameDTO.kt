package com.example.projet_android.api.dto

import java.util.Date

data class GameRequest(
    val name: String
)

data class AddPlayerToGameRequest(
    val username: String
)

data class GameResponse(
    val id: String,
    val status: String,
    val name: String,
    val createdAt: Date,
    val description: String,
    val player: PlayerResponse
)

data class AdminGameResponse(
    val id: String,
    val status: String,
    val name: String,
    val createdAt: Date,
    val description: String,
    val roleMaster: Boolean,
    val players: List<PlayerResponse>
)