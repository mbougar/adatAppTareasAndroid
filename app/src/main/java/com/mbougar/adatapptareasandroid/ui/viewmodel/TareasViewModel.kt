package com.mbougar.adatapptareasandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbougar.adatapptareasandroid.data.model.Tarea
import com.mbougar.adatapptareasandroid.data.remote.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TareasViewModel(private val api: ApiService) : ViewModel() {
    private val _tareas = MutableStateFlow<List<Tarea>>(emptyList())
    val tareas: StateFlow<List<Tarea>> = _tareas

    fun cargarTareas() {
        viewModelScope.launch {
            try {
                val response = api.getTareas("Bearer your_token_here")
                _tareas.value = response
            } catch (e: Exception) {
                // error
            }
        }
    }

    fun agregarTarea(tarea: Tarea) {
        viewModelScope.launch {
            try {
                api.createTarea("Bearer your_token_here", tarea)
                cargarTareas()
            } catch (e: Exception) {
                // error
            }
        }
    }

    fun eliminarTarea(id: String) {
        viewModelScope.launch {
            try {
                api.deleteTarea("Bearer your_token_here", id)
                cargarTareas()
            } catch (e: Exception) {
                // error
            }
        }
    }
}