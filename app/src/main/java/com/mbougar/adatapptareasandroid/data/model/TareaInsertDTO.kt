package com.mbougar.adatapptareasandroid.data.model

data class TareaInsertDTO(
    val titulo: String,
    val desc: String,
    val estado: String = "PENDING",
    val usuario: String
)
