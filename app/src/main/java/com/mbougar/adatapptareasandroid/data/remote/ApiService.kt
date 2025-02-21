package com.mbougar.adatapptareasandroid.data.remote

import com.mbougar.adatapptareasandroid.data.model.LoginResponse
import com.mbougar.adatapptareasandroid.data.model.LoginUsuarioDTO
import com.mbougar.adatapptareasandroid.data.model.Tarea
import com.mbougar.adatapptareasandroid.data.model.UsuarioRegisterDTO
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @POST("/usuarios/login")
    suspend fun login(@Body request: LoginUsuarioDTO): LoginResponse

    @POST("/usuarios/register")
    suspend fun register(@Body request: UsuarioRegisterDTO)

    @GET("/tareas")
    suspend fun getTareas(@Header("Authorization") token: String): List<Tarea>

    @POST("/tareas")
    suspend fun createTarea(@Header("Authorization") token: String, @Body tarea: Tarea)

    @PUT("/tareas/{id}")
    suspend fun updateTarea(@Header("Authorization") token: String, @Path("id") id: String, @Body tarea: Tarea)

    @DELETE("/tareas/{id}")
    suspend fun deleteTarea(@Header("Authorization") token: String, @Path("id") id: String)
}