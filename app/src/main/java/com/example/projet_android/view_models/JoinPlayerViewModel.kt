package com.example.projet_android.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projet_android.navigation.PreferencesHelper
import com.example.projet_android.repositories.GameRepository
import com.example.projet_android.repositories.PlayerRepository
import com.example.projet_android.view_models.states.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinPlayerViewModel @Inject constructor(
    private val gameRepository: GameRepository,
    private val playerRepository: PlayerRepository,
    private val preferencesHelper: PreferencesHelper
) : ViewModel() {
    private val _updatePlayerState = MutableLiveData<RequestState?>()
    val updatePlayerState: MutableLiveData<RequestState?> = _updatePlayerState

    fun updatePlayerName(gameId: String, playerName: String) {
        viewModelScope.launch {
            _updatePlayerState.postValue(RequestState.Loading)
            try {
                val token = preferencesHelper.getToken()!!
                val (_, player) = gameRepository.getGameInfoById(gameId, token)
                playerRepository.updatePlayerName(player.id, playerName, token)
                _updatePlayerState.postValue(RequestState.Success(gameId))
            } catch (e: Exception) {
                println("Error joining the game")
                e.printStackTrace()
                _updatePlayerState.postValue(RequestState.Error("Joining the game failed"))
            }
        }
    }

    fun resetUpdatePlayerState() {
        _updatePlayerState.value = null
    }
}
