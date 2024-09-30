package com.example.projet_android.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.ZoneId
import java.util.Date

data class Game(
    val id: String,
    val name: String,
    val createdAt: Date = Date(),
    val isPlayerAdmin: Boolean = false,
    val status: String = "ACTIVE",
    val description: String? = null,
    val players: List<Player> = listOf()
) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getCreatedDateString(): String {
        return this.createdAt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString()
    }

    fun getSafeDescription(): String {
        return this.description ?: "No description..."
    }
}