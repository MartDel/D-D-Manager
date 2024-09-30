package com.example.projet_android.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projet_android.navigation.PreferencesHelper
import com.example.projet_android.repositories.GameRepository
import com.example.projet_android.view_models.states.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailsViewModel @Inject constructor(
    private val gameRepository: GameRepository,
    private val preferencesHelper: PreferencesHelper
) : ViewModel() {
    private val _fetchGameState = MutableLiveData<RequestState?>()
    val fetchGameState: MutableLiveData<RequestState?> = _fetchGameState

    private val _addPlayerState = MutableLiveData<RequestState?>()
    val addPlayerState: MutableLiveData<RequestState?> = _addPlayerState

    fun fetchAdminGameById(gameId: String) {
        viewModelScope.launch {
            _fetchGameState.postValue(RequestState.Loading)
            try {
                val token = preferencesHelper.getToken()
                val game = gameRepository.getAdminGameById(gameId, token!!)
                _fetchGameState.postValue(RequestState.Success(game))
            } catch (e: Exception) {
                println("Error getting the current game")
                e.printStackTrace()
                _fetchGameState.postValue(RequestState.Error("Getting the current game failed"))
            }
        }
    }

    fun addPlayerToGame(gameId: String, username: String) {
        viewModelScope.launch {
            _addPlayerState.postValue(RequestState.Loading)
            try {
                val token = preferencesHelper.getToken()
                gameRepository.addPlayerToGame(gameId, username, token!!)
                _addPlayerState.postValue(RequestState.Success("OK"))
            } catch (e: Exception) {
                println("Error adding the player to the game")
                e.printStackTrace()
                _addPlayerState.postValue(RequestState.Error("Adding the player to the game failed"))
            }
        }
    }
}
