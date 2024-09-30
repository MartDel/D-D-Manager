package com.example.projet_android.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projet_android.model.Item
import com.example.projet_android.navigation.PreferencesHelper
import com.example.projet_android.repositories.GameRepository
import com.example.projet_android.repositories.PlayerRepository
import com.example.projet_android.view_models.states.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel @Inject constructor(
    private val gameRepository: GameRepository,
    private val playerRepository: PlayerRepository,
    private val preferencesHelper: PreferencesHelper
) : ViewModel() {
    private val _fetchGameState = MutableLiveData<RequestState?>()
    val fetchGameState: MutableLiveData<RequestState?> = _fetchGameState

    private val _usingItemState = MutableLiveData<RequestState?>()
    val usingItemState: MutableLiveData<RequestState?> = _usingItemState

    fun getGameInfo(gameId: String) {
        viewModelScope.launch {
            _fetchGameState.postValue(RequestState.Loading)
            try {
                val token = preferencesHelper.getToken()!!
                val (game, player) = gameRepository.getGameInfoById(gameId, token)
                player.inventory = playerRepository.getPlayerInventory(player.id, token)
                _fetchGameState.postValue(RequestState.Success(Pair(game, player)))
            } catch (e: Exception) {
                println("Error getting game info")
                e.printStackTrace()
                _fetchGameState.postValue(RequestState.Error("Getting game info failed"))
            }
        }
    }

    fun useItem(item: Item) {
        viewModelScope.launch {
            _usingItemState.postValue(RequestState.Loading)
            try {
                val token = preferencesHelper.getToken()!!
                playerRepository.removeItemFromPlayerInventory(item, token)
                _usingItemState.postValue(RequestState.Success("OK"))
            } catch (e: Exception) {
                println("Error using the item")
                e.printStackTrace()
                _usingItemState.postValue(RequestState.Error("Using the item failed"))
            }
        }
    }

    fun resetUsingItemState() {
        _usingItemState.value = null
    }
}
