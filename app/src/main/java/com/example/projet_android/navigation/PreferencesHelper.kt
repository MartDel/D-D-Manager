package com.example.projet_android.navigation

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesHelper @Inject constructor(@ApplicationContext private val context: Context) {

    private val preferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        preferences.edit().putString("jwt_token", token).apply()
    }

    fun getToken(): String? {
        return preferences.getString("jwt_token", null)
    }

    fun clearToken() {
        preferences.edit().remove("jwt_token").apply()
    }
}
