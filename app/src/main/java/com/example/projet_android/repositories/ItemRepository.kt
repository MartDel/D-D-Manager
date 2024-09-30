package com.example.projet_android.repositories

import com.example.projet_android.api.ItemApiService
import com.example.projet_android.model.Item
import javax.inject.Inject

class ItemRepository @Inject constructor(private val itemApiService: ItemApiService) {

    suspend fun getItems(authToken: String): List<Item> {
        return itemApiService.getItems(authToken)
    }
}
