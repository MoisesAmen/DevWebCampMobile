package com.mss.appdevwebcampmovile_v2.models

data class Dashboard(
    val titulo: String,
    val registros: List<Usuario>,
    val ingVirtuales: Double,
    val ingPresenciales: Double,
    val ingresos: Double,
    val menos_disponibles: List<Evento>,
    val mas_disponibles: List<Evento>
)
