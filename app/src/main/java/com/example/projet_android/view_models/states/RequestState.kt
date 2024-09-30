package com.example.projet_android.view_models.states

sealed class RequestState {
    data object Loading : RequestState()
    data class Success<T>(val data: T) : RequestState()
    data class Error(val message: String) : RequestState()
}