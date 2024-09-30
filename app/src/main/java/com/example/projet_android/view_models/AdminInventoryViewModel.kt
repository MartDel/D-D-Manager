package com.example.projet_android.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projet_android.model.Player
import com.example.projet_android.navigation.PreferencesHelper
import com.example.projet_android.repositories.PlayerRepository
import com.example.projet_android.view_models.states.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminInventoryViewModel @Inject constructor(
    private val playerRepository: PlayerRepository,
    private val preferencesHelper: PreferencesHelper
) : ViewModel() {
    private val _selectedPlayer = MutableLiveData<Player?>(null)

    private val _fetchPlayerState = MutableLiveData<RequestState?>()
    val fetchPlayerState: MutableLiveData<RequestState?> = _fetchPlayerState

    fun fetchPlayerInventory(player: Player) {
        viewModelScope.launch {
            _fetchPlayerState.postValue(RequestState.Loading)
            try {
                val token = preferencesHelper.getToken()
                player.inventory = playerRepository.getPlayerInventory(player.id, token!!)
                _selectedPlayer.postValue(player)
                _fetchPlayerState.postValue(RequestState.Success(player))
            } catch (e: Exception) {
                println("Error getting the current player inventory")
                e.printStackTrace()
                _fetchPlayerState.postValue(RequestState.Error("Getting the current player inventory failed"))
            }
        }
    }

    fun addItemToSelectedPlayer(itemId: String) {
        viewModelScope.launch {
            _fetchPlayerState.postValue(RequestState.Loading)
            try {
                val selectedPlayer = _selectedPlayer.value!!
                val token = preferencesHelper.getToken()!!
                playerRepository.addItemToPlayerInventory(selectedPlayer.id, itemId, token)
                fetchPlayerInventory(selectedPlayer)
            } catch (e: Exception) {
                println("Error adding item to the player's inventory")
                e.printStackTrace()
                _fetchPlayerState.postValue(RequestState.Error("Adding item to the player's inventory failed"))
            }
        }
    }
}
