package com.mbougar.adatapptareasandroid.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mbougar.adatapptareasandroid.ui.viewmodel.AuthViewModelLogin

@Composable
fun LoginScreen(viewModel: AuthViewModelLogin = viewModel(), navController: NavController, onLoginSuccess: () -> Unit) {
    // Variables de estado para los campos de entrada y visibilidad de la contraseña
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    // Observamos los estados del ViewModel
    val token by viewModel.token.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()

    // Efecto lanzado cuando el token cambia. Si existe un token, significa que el login fue exitoso
    LaunchedEffect(token) {
        if (token != null) onLoginSuccess()
    }

    // Contenedor principal centrado en la pantalla
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título de la pantalla
            Text("Iniciar Sesión", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(24.dp))

            // Campo de entrada para el nombre de usuario
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Usuario") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Campo de entrada para la contraseña con opción para mostrar u ocultar
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = icon, contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña")
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Indicador de carga si el login está en proceso
            if (loading) CircularProgressIndicator()

            // Muestra un mensaje de error si hay algún problema
            error?.let { Text(it, color = MaterialTheme.colorScheme.error) }

            // Botón para iniciar sesión
            Button(
                onClick = { viewModel.login(username, password) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ingresar")
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Texto clickeable para navegar a la pantalla de registro
            ClickableText(
                text = AnnotatedString("¿No tienes cuenta? Regístrate"),
                onClick = { navController.navigate("register") },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.padding(10.dp))

            // Texto de depuración mostrando las credenciales ingresadas y el token recibido
            Text("Sesión iniciada: $username, $password. \nToken: $token")
        }
    }
}

