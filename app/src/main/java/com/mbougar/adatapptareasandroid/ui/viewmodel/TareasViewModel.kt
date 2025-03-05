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

    private val _tareas = MutableStateFlow<List<Tarea>>(emptyList())
    val tareas: StateFlow<List<Tarea>> = _tareas

    private var token: String? = null

    var tokener: String? = null

    init {
        viewModelScope.launch {
            userPreferences.authToken.collect { storedToken ->
                token = storedToken
                if (token != null) {
                    cargarTareas()
                }
            }
        }
    }

    fun cargarTareas() {
        viewModelScope.launch {
            try {
                token?.let {
                    val response = api.getTareas("Bearer $it")
                    _tareas.value = response
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun actualizarEstadoTarea(id: String, nuevoEstado: String) {
        viewModelScope.launch {
            try {
                token?.let {
                    api.updateTarea("Bearer $it", id, TareaInsertDTO("", "", nuevoEstado, ""))
                    cargarTareas()
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun eliminarTarea(id: String) {
        viewModelScope.launch {
            try {
                token?.let {
                    api.deleteTarea("Bearer $it", id)
                    cargarTareas()
                    _tareas.value = _tareas.value.filter { tarea -> tarea.id != id }
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun crearTarea(titulo: String, descripcion: String, usuario: String) {
        val fecha = Date()
        viewModelScope.launch {
            try {
                token?.let {
                    val nuevaTarea = Tarea(
                        id = null,
                        titulo = titulo,
                        desc = descripcion,
                        usuario = usuario,
                        estado = "PENDING",
                        fechCreacion = "$fecha",
                        fechActualizacion = "$fecha"
                    )
                    api.createTarea("Bearer $it", nuevaTarea)
                    cargarTareas()
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }

}
