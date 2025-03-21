package com.mbougar.adatapptareasandroid.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mbougar.adatapptareasandroid.data.model.UsuarioRegisterDTO
import com.mbougar.adatapptareasandroid.ui.viewmodel.AuthViewModelRegister

@Composable
fun RegisterScreen(
    viewModel: AuthViewModelRegister = viewModel(),
    navController: NavController,
    onRegisterSuccess: () -> Unit
) {
    // Variables de estado para almacenar los valores ingresados en el formulario
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordRepeat by remember { mutableStateOf("") }
    var rol by remember { mutableStateOf("") }
    var calle by remember { mutableStateOf("") }
    var num by remember { mutableStateOf("") }
    var municipio by remember { mutableStateOf("") }
    var provincia by remember { mutableStateOf("") }
    var cp by remember { mutableStateOf("") }
    var ciudad by remember { mutableStateOf("") }

    // Estados para manejar la carga y los errores en el registro
    val loading by viewModel.loading.collectAsState()
    val registerError by viewModel.error.collectAsState()
    val scrollState = rememberScrollState()

    // Estructura de la pantalla de registro
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState), // Permite desplazarse si el contenido es extenso
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título de la pantalla
            Text("Registro", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(24.dp))

            // Campos del formulario de registro
            OutlinedTextField(value = username, onValueChange = { username = it }, label = { Text("Usuario") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
            OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
            OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Contraseña") }, visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth(), singleLine = true)
            OutlinedTextField(value = passwordRepeat, onValueChange = { passwordRepeat = it }, label = { Text("Repetir Contraseña") }, visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth(), singleLine = true)
            OutlinedTextField(value = rol, onValueChange = { rol = it }, label = { Text("Rol") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
            OutlinedTextField(value = calle, onValueChange = { calle = it }, label = { Text("Calle") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
            OutlinedTextField(value = num, onValueChange = { num = it }, label = { Text("Número") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
            OutlinedTextField(value = municipio, onValueChange = { municipio = it }, label = { Text("Municipio") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
            OutlinedTextField(value = provincia, onValueChange = { provincia = it }, label = { Text("Provincia") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
            OutlinedTextField(value = cp, onValueChange = { cp = it }, label = { Text("Código Postal") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
            OutlinedTextField(value = ciudad, onValueChange = { ciudad = it }, label = { Text("Ciudad") }, modifier = Modifier.fillMaxWidth(), singleLine = true)

            Spacer(modifier = Modifier.height(16.dp))

            // Indicador de carga mientras se procesa el registro
            if (loading) CircularProgressIndicator()

            // Mostrar mensaje de error si existe
            registerError?.let { Text(it, color = MaterialTheme.colorScheme.error) }

            // Botón para enviar los datos de registro
            Button(
                onClick = {
                    viewModel.register(
                        UsuarioRegisterDTO(username, email, password, passwordRepeat, rol, calle, num, municipio, provincia, cp, ciudad)
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrarse")
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Texto clickeable para ir a la pantalla de inicio de sesión
            ClickableText(
                text = AnnotatedString("¿Ya tienes cuenta? Inicia sesión"),
                onClick = { navController.navigate("login") },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
