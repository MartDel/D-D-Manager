package com.example.projet_android.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projet_android.navigation.PreferencesHelper
import com.example.projet_android.repositories.ItemRepository
import com.example.projet_android.view_models.states.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val itemRepository: ItemRepository,
    private val preferencesHelper: PreferencesHelper
) : ViewModel() {

    private val _fetchItemsState = MutableLiveData<RequestState?>()
    val fetchItemsState: MutableLiveData<RequestState?> = _fetchItemsState

    fun fetchItems() {
        viewModelScope.launch {
            _fetchItemsState.postValue(RequestState.Loading)
            try {
                val token = preferencesHelper.getToken()
                val items = itemRepository.getItems(token!!)
                _fetchItemsState.postValue(RequestState.Success(items))
            } catch (e: Exception) {
                println("Error getting all items")
                e.printStackTrace()
                _fetchItemsState.postValue(RequestState.Error("Getting all items failed"))
            }
        }
    }
}