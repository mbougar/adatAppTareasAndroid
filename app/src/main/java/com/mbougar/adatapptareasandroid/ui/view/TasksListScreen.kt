package com.mbougar.adatapptareasandroid.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mbougar.adatapptareasandroid.ui.viewmodel.TareasViewModel

@Composable
fun TaskListScreen(viewModel: TareasViewModel) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Tareas")
        viewModel.tareas.forEach { tarea ->
            Text(text = tarea.titulo)
        }
    }
}