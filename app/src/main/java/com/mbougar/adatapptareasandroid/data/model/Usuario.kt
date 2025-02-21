package com.mbougar.adatapptareasandroid.data.model

data class Usuario(
    val _id: String?,
    val username: String,
    val email: String,
    val roles: List<String>,
    val direccion: Direccion?
)