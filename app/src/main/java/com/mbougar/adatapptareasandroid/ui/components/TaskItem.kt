package com.mbougar.adatapptareasandroid.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mbougar.adatapptareasandroid.data.model.Tarea
import com.mbougar.adatapptareasandroid.ui.viewmodel.TareasViewModel

@Composable
fun TaskItem(tarea: Tarea, viewModel: TareasViewModel) {
    // Estado local para manejar si la tarea está marcada como completada
    var isChecked by remember { mutableStateOf(tarea.estado != "PENDING") }

    // Tarjeta contenedora de la información de la tarea
    Card(
        modifier = Modifier
            .fillMaxWidth()  // Ocupa todo el ancho disponible
            .padding(8.dp),  // Margen alrededor de la tarjeta
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {  // Contenedor vertical con padding interno
            // Muestra el título de la tarea
            Text(text = tarea.titulo, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)

            // Muestra el usuario asignado a la tarea
            Text(text = tarea.usuario, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)

            // Muestra la descripción de la tarea si está disponible
            tarea.desc?.let {
                Text(text = it, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }

            // Fila que contiene el estado de la tarea y acciones
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween  // Distribuye los elementos equitativamente
            ) {
                // Fila con el estado de la tarea y el checkbox
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Estado: ${tarea.estado}", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
                    Spacer(modifier = Modifier.width(8.dp))  // Espaciado entre el texto y el checkbox

                    // Checkbox para cambiar el estado de la tarea
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = {
                            isChecked = it
                            val newEstado = if (it) "DONE" else "PENDING"
                            viewModel.actualizarEstadoTarea(tarea.id!!, newEstado)  // Llamada para actualizar el estado
                        }
                    )
                }

                // Botón para eliminar la tarea
                IconButton(onClick = { viewModel.eliminarTarea(tarea.id!!) }) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar tarea", tint = Color.Red)
                }
            }
        }
    }
}
