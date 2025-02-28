package com.mbougar.adatapptareasandroid.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mbougar.adatapptareasandroid.data.model.Tarea
import com.mbougar.adatapptareasandroid.ui.viewmodel.TareasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(viewModel: TareasViewModel = viewModel(), navController: NavController) {
    val tareas by viewModel.tareas.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarTareas()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Lista de Tareas", style = MaterialTheme.typography.headlineMedium) })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* Navigate to add task screen */ }, containerColor = MaterialTheme.colorScheme.primary) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Tarea", tint = Color.White)
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues), contentAlignment = Alignment.Center) {
            if (tareas.isEmpty()) {
                Column {
                    Text("No hay tareas disponibles", style = MaterialTheme.typography.bodyLarge, color = Color.Gray)
                    Text("${viewModel.tokener}")
                }
            } else {
                LazyColumn(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)) {
                    items(tareas) { tarea ->
                        TaskItem(tarea, viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun TaskItem(tarea: Tarea, viewModel: TareasViewModel) {
    var isChecked by remember { mutableStateOf(tarea.estado != "PENDING") }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = tarea.titulo, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
            Text(text = "${tarea.id}", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
            tarea.desc?.let { Text(text = it, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant) }
            Text(text = "Usuario: ${tarea.usuario}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(text = "Fecha Creación: ${tarea.fechCreacion}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(text = "Última Actualización: ${tarea.fechActualizacion}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Estado: ${tarea.estado}", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
                Spacer(modifier = Modifier.width(8.dp))
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = {
                        isChecked = it
                        val newEstado = if (it) "DONE" else "PENDING"
                        viewModel.actualizarEstadoTarea(tarea.id!!, newEstado)
                    }
                )
            }
        }
    }
}

