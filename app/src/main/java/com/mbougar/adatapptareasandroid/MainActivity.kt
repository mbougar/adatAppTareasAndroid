package com.mbougar.adatapptareasandroid

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mbougar.adatapptareasandroid.data.remote.ApiService
import com.mbougar.adatapptareasandroid.data.remote.RetrofitInstance
import com.mbougar.adatapptareasandroid.ui.view.LoginScreen
import com.mbougar.adatapptareasandroid.ui.view.TaskListScreen
import com.mbougar.adatapptareasandroid.ui.viewmodel.TareasViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val api = RetrofitInstance(this).api
        val viewModel: TareasViewModel by viewModels { object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return TareasViewModel(api) as T
            }
        } }
        setContent {
            App(viewModel)
        }
    }
}

@Composable
fun App(viewModel: TareasViewModel) {
    var isLoggedIn by remember { mutableStateOf(false) }
    if (isLoggedIn) {
        TaskListScreen(viewModel)
    } else {
        LoginScreen(viewModel) { isLoggedIn = true }
    }
}

class MyApp : Application() {
    lateinit var api: ApiService
    override fun onCreate() {
        super.onCreate()
        api = RetrofitInstance(this).api
    }
}
