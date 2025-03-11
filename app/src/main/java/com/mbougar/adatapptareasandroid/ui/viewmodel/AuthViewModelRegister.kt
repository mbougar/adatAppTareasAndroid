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

    // Flow para manejar el estado de carga (indica si el registro está en proceso)
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading  // Exposición del estado de carga

    // Flow para manejar mensajes de error
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error  // Exposición del error como StateFlow de solo lectura

    // Función para registrar un nuevo usuario
    fun register(user: UsuarioRegisterDTO) {
        viewModelScope.launch {  // Se ejecuta en un scope de ViewModel para manejar corrutinas
            _loading.value = true  // Indica que el registro está en proceso
            _error.value = null  // Resetea posibles errores previos
            try {
                // Llamada a la API para registrar al usuario
                api.register(user)
            } catch (e: Exception) {
                // Manejo de error en caso de fallo en el registro
                _error.value = "Error en el registro"
            }
            _loading.value = false  // Finaliza el estado de carga
        }
    }
}

