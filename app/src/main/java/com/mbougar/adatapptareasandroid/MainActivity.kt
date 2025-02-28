package com.mbougar.adatapptareasandroid

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.*
import com.mbougar.adatapptareasandroid.data.remote.RetrofitInstance
import com.mbougar.adatapptareasandroid.data.repository.UserPreferences
import com.mbougar.adatapptareasandroid.navigation.AppNavigation
import com.mbougar.adatapptareasandroid.ui.theme.AdatAppTareasAndroidTheme

import com.mbougar.adatapptareasandroid.ui.viewmodel.AuthViewModelLogin
import com.mbougar.adatapptareasandroid.ui.viewmodel.AuthViewModelRegister
import com.mbougar.adatapptareasandroid.ui.viewmodel.TareasViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AdatAppTareasAndroidTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }
    val navController = rememberNavController()
    val authViewModelLogin: AuthViewModelLogin = AuthViewModelLogin(api = RetrofitInstance.getApiService(), userPreferences = userPreferences)
    val authViewModelRegister: AuthViewModelRegister = AuthViewModelRegister(api = RetrofitInstance.getApiService())
    val tareasViewModel: TareasViewModel = TareasViewModel(api = RetrofitInstance.getApiService(), userPreferences)

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            AppNavigation(navController, authViewModelLogin, authViewModelRegister, tareasViewModel)
        }
    }
}

