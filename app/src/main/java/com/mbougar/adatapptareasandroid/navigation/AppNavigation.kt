package com.mbougar.adatapptareasandroid.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mbougar.adatapptareasandroid.ui.view.LoginScreen
import com.mbougar.adatapptareasandroid.ui.view.RegisterScreen
import com.mbougar.adatapptareasandroid.ui.view.TaskListScreen
import com.mbougar.adatapptareasandroid.ui.viewmodel.AuthViewModelLogin
import com.mbougar.adatapptareasandroid.ui.viewmodel.AuthViewModelRegister
import com.mbougar.adatapptareasandroid.ui.viewmodel.TareasViewModel

@Composable
fun AppNavigation(navController: NavHostController, authViewModelLogin: AuthViewModelLogin, authViewModelRegister: AuthViewModelRegister, tareasViewModel: TareasViewModel) {

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(viewModel = authViewModelLogin, navController) {
                navController.navigate("taskList") { popUpTo("login") { inclusive = true } }
            }
        }
        composable("register") {
            RegisterScreen(viewModel = authViewModelRegister, navController) {
                navController.navigate("login")
            }
        }
        composable("taskList") {
            TaskListScreen(viewModel = tareasViewModel, navController = navController)
        }
    }
}