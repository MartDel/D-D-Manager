package com.example.projet_android.repositories

import com.example.projet_android.api.PlayerApiService
import com.example.projet_android.api.dto.UpdatePlayerRequest
import com.example.projet_android.model.Inventory
import com.example.projet_android.model.Item
import javax.inject.Inject

class PlayerRepository @Inject constructor(private val playerApiService: PlayerApiService) {

    suspend fun getPlayerInventory(playerId: String, authToken: String): Inventory {
        val response = playerApiService.getPlayerInventory(playerId, authToken)
        return Inventory(items = response.map { inventoryResponse ->
            Item(
                id = inventoryResponse.item.id,
                name = inventoryResponse.item.name,
                type = inventoryResponse.item.type,
                description = inventoryResponse.item.description,
                inventoryId = inventoryResponse.id
            )
        })
    }

    suspend fun addItemToPlayerInventory(playerId: String, itemId: String, authToken: String) {
        val response = playerApiService.addItemToPlayerInventory(playerId, itemId, authToken)
        if (!response.isSuccessful) {
            throw Exception("Adding item to player's inventory failed: ${response.errorBody()?.string()}")
        }
    }

    suspend fun removeItemFromPlayerInventory(item: Item, authToken: String) {
        val response = playerApiService.removeItemFromInventory(item.inventoryId, authToken)
        if (!response.isSuccessful) {
            throw Exception("Removing item from player's inventory failed: ${response.errorBody()?.string()}")
        }
    }

    suspend fun updatePlayerName(playerId: String, playerName: String, authToken: String) {
        val request = UpdatePlayerRequest(playerName)
        val response = playerApiService.updatePlayer(playerId, request, authToken)
        if (!response.isSuccessful) {
            throw Exception("Updating player failed: ${response.errorBody()?.string()}")
        }
    }
}
