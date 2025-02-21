package com.mbougar.adatapptareasandroid.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mbougar.adatapptareasandroid.data.model.UsuarioRegisterDTO
import com.mbougar.adatapptareasandroid.ui.viewmodel.AuthViewModel

@Composable
fun RegisterScreen(viewModel: AuthViewModel = viewModel(), onRegisterSuccess: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordRepeat by remember { mutableStateOf("") }
    var calle by remember { mutableStateOf("") }
    var num by remember { mutableStateOf("") }
    var municipio by remember { mutableStateOf("") }
    var provincia by remember { mutableStateOf("") }
    var cp by remember { mutableStateOf("") }
    var ciudad by remember { mutableStateOf("") }

    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Registro", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = username, onValueChange = { username = it }, label = { Text("Usuario") })
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Contraseña") }, visualTransformation = PasswordVisualTransformation())
        OutlinedTextField(value = passwordRepeat, onValueChange = { passwordRepeat = it }, label = { Text("Repetir Contraseña") }, visualTransformation = PasswordVisualTransformation())
        OutlinedTextField(value = calle, onValueChange = { calle = it }, label = { Text("Calle") })
        OutlinedTextField(value = num, onValueChange = { num = it }, label = { Text("Número") })
        OutlinedTextField(value = municipio, onValueChange = { municipio = it }, label = { Text("Municipio") })
        OutlinedTextField(value = provincia, onValueChange = { provincia = it }, label = { Text("Provincia") })
        OutlinedTextField(value = cp, onValueChange = { cp = it }, label = { Text("Código Postal") })
        OutlinedTextField(value = ciudad, onValueChange = { ciudad = it }, label = { Text("Ciudad") })

        Spacer(modifier = Modifier.height(16.dp))

        if (loading) CircularProgressIndicator()
        error?.let { Text(it, color = MaterialTheme.colorScheme.error) }

        Button(onClick = {
            viewModel.register(
                UsuarioRegisterDTO(username, email, password, passwordRepeat, "USER", calle, num, municipio, provincia, cp, ciudad)
            )
        }) {
            Text("Registrarse")
        }
    }
}