package com.example.projet_android.model

import android.annotation.SuppressLint
import android.content.Context

data class Item(
    val id: String,
    val name: String,
    val type: String,
    val description: String = "",
    val inventoryId: String = ""
) {
    @SuppressLint("DiscouragedApi")
    fun getResId(context: Context): Int {
        return context.resources.getIdentifier(this.type, "drawable", context.packageName)
    }
}