package com.mbougar.adatapptareasandroid.data.model

// DTO de registro de usuario
data class UsuarioRegisterDTO(
    val username: String,
    val email: String,
    val password: String,
    val passwordRepeat: String,
    val rol: String?,
    val calle: String,
    val num: String,
    val municipio: String,
    val provincia: String,
    val cp: String,
    val ciudad: String
)
