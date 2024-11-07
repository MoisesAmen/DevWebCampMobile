package com.mss.appdevwebcampmovile_v2.models

data class Evento(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val disponibles: String,
    val categoria: Categoria,
    val dia: Dia,
    val hora: Hora,
    val ponente: Ponente
)
