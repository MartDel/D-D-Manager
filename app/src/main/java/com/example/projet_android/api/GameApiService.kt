package com.example.projet_android.api

import com.example.projet_android.api.dto.AddPlayerToGameRequest
import com.example.projet_android.api.dto.AddedPlayerResponse
import com.example.projet_android.api.dto.AdminGameResponse
import com.example.projet_android.api.dto.GameRequest
import com.example.projet_android.api.dto.GameResponse
import com.example.projet_android.model.Game
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface GameApiService {

    @POST("party/create")
    suspend fun addGame(
        @Body request: GameRequest,
        @Header("Authorization") authToken: String
    ): Game

    @GET("party")
    suspend fun getGames(@Header("Authorization") authToken: String): List<GameResponse>

    @GET("party/{id}")
    suspend fun getAdminGameById(
        @Path("id") gameId: String,
        @Header("Authorization") authToken: String
    ): AdminGameResponse

    @POST("party/{id}/invite")
    suspend fun addPlayerToGame(
        @Path("id") gameId: String,
        @Body request: AddPlayerToGameRequest,
        @Header("Authorization") authToken: String
    ): Response<AddedPlayerResponse>
}
