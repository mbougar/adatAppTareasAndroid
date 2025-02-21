package com.mbougar.adatapptareasandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbougar.adatapptareasandroid.data.model.LoginUsuarioDTO
import com.mbougar.adatapptareasandroid.data.model.UsuarioRegisterDTO
import com.mbougar.adatapptareasandroid.data.remote.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModelRegister(private val api: ApiService) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    fun register(user: UsuarioRegisterDTO) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                api.register(user)
            } catch (e: Exception) {
                _error.value = "Error en el registro"
            }
            _loading.value = false
        }
    }
}
