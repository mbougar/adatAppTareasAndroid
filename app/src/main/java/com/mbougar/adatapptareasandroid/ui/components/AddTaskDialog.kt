package com.mbougar.adatapptareasandroid.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mbougar.adatapptareasandroid.ui.viewmodel.TareasViewModel

@Composable
fun AddTaskDialog(onDismiss: () -> Unit, viewModel: TareasViewModel) {
    // Variables de estado para almacenar los valores ingresados por el usuario
    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var usuario by remember { mutableStateOf("") }

    // Diálogo emergente para agregar una nueva tarea
    AlertDialog(
        onDismissRequest = onDismiss, // Acción al cerrar el diálogo
        title = { Text("Agregar Nueva Tarea") }, // Título del cuadro de diálogo
        text = {
            Column {
                // Campo de texto para el título de la tarea
                OutlinedTextField(
                    value = titulo,
                    onValueChange = { titulo = it },
                    label = { Text("Título") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Campo de texto para la descripción de la tarea
                OutlinedTextField(
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    label = { Text("Descripción") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Campo de texto para el usuario asignado a la tarea
                OutlinedTextField(
                    value = usuario,
                    onValueChange = { usuario = it },
                    label = { Text("Usuario") },
                    singleLine = true
                )
            }
        },
        confirmButton = {
            // Botón para confirmar la creación de la tarea
            Button(
                onClick = {
                    viewModel.crearTarea(titulo, descripcion, usuario)
                    onDismiss() // Cierra el diálogo después de agregar la tarea
                }
            ) {
                Text("Agregar")
            }
        },
        dismissButton = {
            // Botón para cancelar y cerrar el diálogo sin agregar la tarea
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}