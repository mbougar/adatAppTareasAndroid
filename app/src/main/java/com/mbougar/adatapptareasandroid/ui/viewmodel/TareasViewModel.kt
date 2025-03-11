package com.mbougar.adatapptareasandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbougar.adatapptareasandroid.data.model.Tarea
import com.mbougar.adatapptareasandroid.data.model.TareaInsertDTO
import com.mbougar.adatapptareasandroid.data.remote.ApiService
import com.mbougar.adatapptareasandroid.data.repository.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date

class TareasViewModel(
    private val api: ApiService,
    private val userPreferences: UserPreferences
) : ViewModel() {

    // Flow que almacena la lista de tareas y se actualiza dinámicamente
    private val _tareas = MutableStateFlow<List<Tarea>>(emptyList())
    val tareas: StateFlow<List<Tarea>> = _tareas  // Exposición del estado de solo lectura

    private var token: String? = null  // Token de autenticación recuperado de las preferencias

    init {
        viewModelScope.launch {
            userPreferences.authToken.collect { storedToken ->
                token = storedToken
                if (token != null) {
                    cargarTareas() // Carga las tareas automáticamente si hay un token almacenado
                }
            }
        }
    }

    // Función para obtener la lista de tareas desde la API
    fun cargarTareas() {
        viewModelScope.launch {
            try {
                token?.let {
                    val response = api.getTareas("Bearer $it")
                    _tareas.value = response  // Actualiza la lista de tareas con la respuesta de la API
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    // Función para actualizar el estado de una tarea específica
    fun actualizarEstadoTarea(id: String, nuevoEstado: String) {
        viewModelScope.launch {
            try {
                token?.let {
                    api.updateTarea("Bearer $it", id, TareaInsertDTO("", "", nuevoEstado, ""))
                    cargarTareas() // Recarga la lista de tareas después de la actualización
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    // Función para eliminar una tarea
    fun eliminarTarea(id: String) {
        viewModelScope.launch {
            try {
                // Primero actualizamos la UI eliminando la tarea localmente
                // Esto evita una actualización tardía si la petición tarda en completarse
                _tareas.value = _tareas.value.filter { tarea -> tarea.id != id }

                token?.let {
                    api.deleteTarea("Bearer $it", id)
                }
            } catch (e: Exception) {
                println(e)
                cargarTareas() // Si hay un error, recargar la lista para corregir la UI
            }
        }
    }

    // Función para crear una nueva tarea
    fun crearTarea(titulo: String, descripcion: String, usuario: String) {
        val fecha = Date() // Fecha actual para la tarea
        viewModelScope.launch {
            try {
                token?.let {
                    val nuevaTarea = Tarea(
                        id = null,  // Se generará automáticamente en la base de datos
                        titulo = titulo,
                        desc = descripcion,
                        usuario = usuario,
                        estado = "PENDING",
                        fechCreacion = "$fecha",
                        fechActualizacion = "$fecha"
                    )
                    api.createTarea("Bearer $it", nuevaTarea)
                    cargarTareas() // Recargar tareas después de la creación
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}

