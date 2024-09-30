package com.example.projet_android.api

import com.example.projet_android.api.dto.AddedPlayerResponse
import com.example.projet_android.api.dto.CreateInventoryResponse
import com.example.projet_android.api.dto.DeleteResponse
import com.example.projet_android.api.dto.InventoryResponse
import com.example.projet_android.api.dto.UpdatePlayerRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface PlayerApiService {

    @GET("players/{id}/inventory")
    suspend fun getPlayerInventory(
        @Path("id") playerId: String,
        @Header("Authorization") authToken: String
    ): List<InventoryResponse>

    @POST("players/{playerId}/items/{itemId}")
    suspend fun addItemToPlayerInventory(
        @Path("playerId") playerId: String,
        @Path("itemId") itemId: String,
        @Header("Authorization") authToken: String
    ): Response<CreateInventoryResponse>

    @POST("players/{id}")
    suspend fun updatePlayer(
        @Path("id") playerId: String,
        @Body request: UpdatePlayerRequest,
        @Header("Authorization") authToken: String
    ): Response<AddedPlayerResponse>

    @DELETE("inventory/{id}")
    suspend fun removeItemFromInventory(
        @Path("id") inventoryId: String,
        @Header("Authorization") authToken: String
    ): Response<DeleteResponse>
}
