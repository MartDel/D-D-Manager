package com.example.projet_android.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projet_android.navigation.PreferencesHelper
import com.example.projet_android.repositories.AuthRepository
import com.example.projet_android.view_models.states.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val preferencesHelper: PreferencesHelper
) : ViewModel() {

    private val _loginState = MutableLiveData<RequestState?>()
    val loginState: MutableLiveData<RequestState?> = _loginState

    fun register(username: String, email: String, password: String) {
        viewModelScope.launch {
            _loginState.postValue(RequestState.Loading)
            try {
                authRepository.register(username, email, password)
                login(email, password)
            } catch (e: Exception) {
                println("Error registering")
                e.printStackTrace()
                _loginState.postValue(RequestState.Error("Register failed"))
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.postValue(RequestState.Loading)
            try {
                val token = authRepository.login(email, password)
                preferencesHelper.saveToken(token)
                _loginState.postValue(RequestState.Success(token))
            } catch (e: Exception) {
                println("Error login")
                e.printStackTrace()
                _loginState.postValue(RequestState.Error("Login failed"))
            }
        }
    }

    fun isConnected(): Boolean {
        return preferencesHelper.getToken() != null
    }

    fun logout() {
        preferencesHelper.clearToken()
    }

    fun resetLoginState() {
        _loginState.value = null
    }
}
