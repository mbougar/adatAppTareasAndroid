package com.mbougar.adatapptareasandroid.data.model

data class Tarea(
    val id: String?,
    val titulo: String,
    val desc: String?,
    val estado: String,
    val usuario: String,
    val fechCreacion: String,
    val fechActualizacion: String
)