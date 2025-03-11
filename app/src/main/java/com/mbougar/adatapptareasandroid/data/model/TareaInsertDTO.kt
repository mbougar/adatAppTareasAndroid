package com.mbougar.adatapptareasandroid.data.model


// DTO de insert de una tarea
data class TareaInsertDTO(
    val titulo: String,
    val desc: String,
    val estado: String = "PENDING",
    val usuario: String
)
