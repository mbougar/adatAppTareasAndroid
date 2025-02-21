package com.mbougar.adatapptareasandroid.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mbougar.adatapptareasandroid.ui.view.LoginScreen
import com.mbougar.adatapptareasandroid.ui.view.RegisterScreen
import com.mbougar.adatapptareasandroid.ui.view.TaskListScreen
import com.mbougar.adatapptareasandroid.ui.viewmodel.AuthViewModel
import com.mbougar.adatapptareasandroid.ui.viewmodel.TareasViewModel

@Composable
fun AppNavigation(navController: NavHostController, authViewModel: AuthViewModel, tareasViewModel: TareasViewModel) {
    val token = authViewModel.token.collectAsState().value

    NavHost(navController = navController, startDestination = if (token == null) "login" else "taskList") {
        composable("login") {
            LoginScreen(viewModel = authViewModel) {
                navController.navigate("taskList") { popUpTo("login") { inclusive = true } }
            }
        }
        composable("register") {
            RegisterScreen(viewModel = authViewModel) {
                navController.navigate("login")
            }
        }
        composable("taskList") {
            TaskListScreen(viewModel = tareasViewModel, navController = navController)
        }
    }
}