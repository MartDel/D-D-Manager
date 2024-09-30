package com.example.projet_android.model

data class Player(
    val id: String,
    val name: String,
    var inventory: Inventory,
    val isBan: Boolean = false
) {}