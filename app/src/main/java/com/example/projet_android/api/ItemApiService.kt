package com.example.projet_android.api

import com.example.projet_android.model.Item
import retrofit2.http.GET
import retrofit2.http.Header

interface ItemApiService {

    @GET("items")
    suspend fun getItems(@Header("Authorization") authToken: String): List<Item>
}
