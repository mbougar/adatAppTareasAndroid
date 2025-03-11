package com.mbougar.adatapptareasandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbougar.adatapptareasandroid.data.model.LoginUsuarioDTO
import com.mbougar.adatapptareasandroid.data.remote.ApiService
import com.mbougar.adatapptareasandroid.data.repository.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModelLogin(private val api: ApiService, private val userPreferences: UserPreferences) : ViewModel() {

    // Flow para almacenar el token de autenticación
    private val _token = MutableStateFlow<String?>(null)
    val token: StateFlow<String?> get() = _token  // Exposición del token como StateFlow de solo lectura

    // Flow para manejar el estado de carga (indica si la autenticación está en proceso)
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading  // Exposición del estado de carga

    // Flow para manejar mensajes de error
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error  // Exposición del error como StateFlow de solo lectura

    // Función para iniciar sesión
    fun login(username: String, password: String) {
        viewModelScope.launch {  // Se ejecuta en un scope de ViewModel para manejo de corrutinas
            _loading.value = true  // Indica que la autenticación está en proceso
            _error.value = null  // Resetea posibles errores previos
            try {
                // Llamada a la API para autenticar al usuario
                val response = api.login(LoginUsuarioDTO(username, password))

                // Guarda el token recibido en las preferencias del usuario
                userPreferences.saveAuthToken("${response.token}")

                // Actualiza el estado del token con la respuesta de la API
                _token.value = response.token
            } catch (e: Exception) {
                // Manejo de error en caso de fallo en la autenticación
                _error.value = "Error de autenticación"
            }
            _loading.value = false  // Finaliza el estado de carga
        }
    }
}

