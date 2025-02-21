package com.mbougar.adatapptareasandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.mbougar.adatapptareasandroid.data.remote.RetrofitInstance
import com.mbougar.adatapptareasandroid.navigation.AppNavigation
import com.mbougar.adatapptareasandroid.ui.theme.AdatAppTareasAndroidTheme

import com.mbougar.adatapptareasandroid.ui.viewmodel.AuthViewModel
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
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = AuthViewModel(api = RetrofitInstance.api)
    val tareasViewModel: TareasViewModel = TareasViewModel(api = RetrofitInstance.api)

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            AppNavigation(navController, authViewModel, tareasViewModel)
        }
    }
}

