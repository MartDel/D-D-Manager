package com.example.projet_android.repositories

import com.example.projet_android.api.GameApiService
import com.example.projet_android.api.dto.AddPlayerToGameRequest
import com.example.projet_android.api.dto.GameRequest
import com.example.projet_android.model.Game
import com.example.projet_android.model.Inventory
import com.example.projet_android.model.Player
import javax.inject.Inject

class GameRepository @Inject constructor(private val gameApiService: GameApiService) {

    suspend fun addGame(name: String, authToken: String): Game {
        val request = GameRequest(name)
        return gameApiService.addGame(request, authToken)
    }

    suspend fun getGames(authToken: String): List<Game> {
        val response = gameApiService.getGames(authToken)
        return response.map { gameResponse ->
            Game(
                id = gameResponse.id,
                name = gameResponse.name,
                createdAt = gameResponse.createdAt,
                status = gameResponse.status,
                description = gameResponse.description,
                isPlayerAdmin = gameResponse.player.roleMaster
            )
        }
    }

    suspend fun getGameInfoById(gameId: String, authToken: String): Pair<Game, Player> {
        val response = gameApiService.getGames(authToken)
        val gameResponse = response.find { it.id == gameId }
        if (gameResponse == null)
            throw Exception("Could not find the wished game")
        val playerResponse = gameResponse.player
        val game = Game(
            id = gameResponse.id,
            name = gameResponse.name,
            createdAt = gameResponse.createdAt,
            status = gameResponse.status,
            description = gameResponse.description,
            isPlayerAdmin = gameResponse.player.roleMaster
        )
        val player = Player(
            id = playerResponse.id,
            name = if (playerResponse.status == "ACTIVE") playerResponse.firstname else playerResponse.user.username,
            inventory = Inventory()
        )
        return Pair(game, player)
    }

    suspend fun getAdminGameById(gameId: String, authToken: String): Game {
        val response = gameApiService.getAdminGameById(gameId, authToken)
        return Game(
            id = response.id,
            name = response.name,
            createdAt = response.createdAt,
            status = response.status,
            description = response.description,
            isPlayerAdmin = response.roleMaster,
            players = response.players.map { responsePlayer ->
                Player(
                    id = responsePlayer.id,
                    name = if (responsePlayer.status == "ACTIVE") responsePlayer.firstname else responsePlayer.user.username,
                    inventory = Inventory()
                )
            }
        )
    }

    suspend fun addPlayerToGame(gameId: String, username: String, authToken: String) {
        val request = AddPlayerToGameRequest(username)
        val response = gameApiService.addPlayerToGame(gameId, request, authToken)
        if (!response.isSuccessful) {
            throw Exception("Adding player failed: ${response.errorBody()?.string()}")
        }
    }
}
