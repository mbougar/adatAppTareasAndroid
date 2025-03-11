package com.mbougar.adatapptareasandroid.data.model


// Clase que define la direccion del domicilio de un usuario
data class Direccion(
    val calle: String,
    val numero: String,
    val ciudad: String,
    val municipio: String,
    val provincia: String,
    val cp: String
)