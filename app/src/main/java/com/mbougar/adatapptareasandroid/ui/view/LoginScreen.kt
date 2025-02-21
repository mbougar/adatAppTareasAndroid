package com.mbougar.adatapptareasandroid.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mbougar.adatapptareasandroid.ui.viewmodel.TareasViewModel

@Composable
fun LoginScreen(viewModel: TareasViewModel, onLoginSuccess: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Login")
        OutlinedTextField(value = username, onValueChange = { username = it })
        OutlinedTextField(value = password, onValueChange = { password = it })
        Button(onClick = { viewModel.cargarTareas(); onLoginSuccess() }) {
            Text("Login")
        }
    }
}