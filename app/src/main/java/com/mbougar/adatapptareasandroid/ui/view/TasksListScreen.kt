package com.mbougar.adatapptareasandroid.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mbougar.adatapptareasandroid.ui.components.AddTaskDialog
import com.mbougar.adatapptareasandroid.ui.components.TaskItem
import com.mbougar.adatapptareasandroid.ui.viewmodel.TareasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(viewModel: TareasViewModel = viewModel(), navController: NavController) {
    // Estado que contiene la lista de tareas obtenida del ViewModel
    val tareas by viewModel.tareas.collectAsState()
    // Estado para controlar la visibilidad del diálogo de agregar tarea
    var showDialog by remember { mutableStateOf(false) }

    // Efecto lanzado cuando se compone la pantalla, carga las tareas desde la API
    LaunchedEffect(Unit) {
        viewModel.cargarTareas()
    }

    Scaffold(
        // Barra superior con el título y un botón de actualización
        topBar = {
            TopAppBar(title = { Text("Lista de Tareas", style = MaterialTheme.typography.headlineMedium) },
                actions = {
                    IconButton(
                        onClick =  {
                            viewModel.cargarTareas() // Recargar tareas manualmente
                        }
                    ) {
                        Icon(Icons.Default.Refresh, contentDescription = "Actualizar", tint = Color.Black)
                    }
                }
            )
        },
        // Botón flotante para agregar una nueva tarea
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true }, // Mostrar el diálogo de agregar tarea
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Tarea", tint = Color.White)
            }
        }
    ) { paddingValues ->
        // Contenedor principal de la pantalla
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            // Mostrar mensaje si no hay tareas, o la lista de tareas si existen
            if (tareas.isEmpty()) {
                Text("No hay tareas disponibles", style = MaterialTheme.typography.bodyLarge, color = Color.Gray)
            } else {
                LazyColumn(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)) {
                    items(tareas) { tarea ->
                        TaskItem(tarea, viewModel) // Componente para mostrar cada tarea individualmente
                    }
                }
            }
        }
    }

    // Mostrar diálogo de agregar tarea si showDialog es true
    if (showDialog) {
        AddTaskDialog(onDismiss = { showDialog = false }, viewModel = viewModel)
    }
}