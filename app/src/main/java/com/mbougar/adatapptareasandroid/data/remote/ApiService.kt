package com.mbougar.adatapptareasandroid.data.remote

import com.mbougar.adatapptareasandroid.data.model.LoginRequest
import com.mbougar.adatapptareasandroid.data.model.LoginResponse
import com.mbougar.adatapptareasandroid.data.model.RegisterRequest
import com.mbougar.adatapptareasandroid.data.model.Tarea
import com.mbougar.adatapptareasandroid.data.model.TareaRequest
import com.mbougar.adatapptareasandroid.data.model.Usuario
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @POST("usuarios/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("usuarios/register")
    suspend fun register(@Body request: RegisterRequest): Usuario

    @GET("usuarios/{id}")
    suspend fun getUsuario(@Path("id") id: String): Usuario

    @GET("tareas")
    suspend fun getTareas(): List<Tarea>

    @POST("tareas")
    suspend fun crearTarea(@Body request: TareaRequest): Tarea

    @PUT("tareas/{id}")
    suspend fun actualizarTarea(@Path("id") id: String, @Body request: TareaRequest): Tarea

    @DELETE("tareas/{id}")
    suspend fun eliminarTarea(@Path("id") id: String)
}