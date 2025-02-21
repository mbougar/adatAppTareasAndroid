package com.mbougar.adatapptareasandroid.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbougar.adatapptareasandroid.data.model.Tarea
import com.mbougar.adatapptareasandroid.data.remote.ApiService
import kotlinx.coroutines.launch

class TareasViewModel(private val api: ApiService) : ViewModel() {
    var tareas by mutableStateOf(listOf<Tarea>())
        private set

    fun cargarTareas() {
        viewModelScope.launch {
            tareas = api.getTareas()
        }
    }
}