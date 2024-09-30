package com.example.projet_android.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projet_android.model.Game
import com.example.projet_android.navigation.PreferencesHelper
import com.example.projet_android.repositories.GameRepository
import com.example.projet_android.view_models.states.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameRepository: GameRepository,
    private val preferencesHelper: PreferencesHelper
) : ViewModel() {
    private val _createGameState = MutableLiveData<RequestState?>()
    val createGameState: MutableLiveData<RequestState?> = _createGameState

    private val _games = MutableStateFlow<List<Game>>(emptyList())
    val games: StateFlow<List<Game>> get() = _games
    private val _fetchGamesState = MutableLiveData<RequestState?>()
    val fetchGamesState: MutableLiveData<RequestState?> = _fetchGamesState

    fun addGame(name: String) {
        viewModelScope.launch {
            createGameState.postValue(RequestState.Loading)
            try {
                val token = preferencesHelper.getToken()
                val createdGame = gameRepository.addGame(name, token!!)
                _createGameState.postValue(RequestState.Success(createdGame.name))
            } catch (e: Exception) {
                println("Error adding the game")
                e.printStackTrace()
                _createGameState.postValue(RequestState.Error("Adding game failed"))
            }
        }
    }

    fun fetchGames() {
        viewModelScope.launch {
            _fetchGamesState.postValue(RequestState.Loading)
            try {
                val token = preferencesHelper.getToken()
                _games.value = gameRepository.getGames(token!!)
                _fetchGamesState.postValue(RequestState.Success(_games))
            } catch (e: Exception) {
                println("Error getting games")
                e.printStackTrace()
                _fetchGamesState.postValue(RequestState.Error("Getting games failed"))
            }
        }
    }

    fun resetCreateGameState() {
        _createGameState.value = null
    }
}
