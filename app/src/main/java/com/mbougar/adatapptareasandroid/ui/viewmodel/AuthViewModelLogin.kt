package com.mbougar.adatapptareasandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbougar.adatapptareasandroid.data.model.LoginUsuarioDTO
import com.mbougar.adatapptareasandroid.data.remote.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModelLogin(private val api: ApiService) : ViewModel() {

    private val _token = MutableStateFlow<String?>(null)
    val token: StateFlow<String?> get() = _token

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                val response = api.login(LoginUsuarioDTO(username, password))
                _token.value = response.token
            } catch (e: Exception) {
                _error.value = "Error de autenticación"
            }
            _loading.value = false
        }
    }
}
