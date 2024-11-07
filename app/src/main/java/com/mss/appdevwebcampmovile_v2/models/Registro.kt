package com.mss.appdevwebcampmovile_v2.models

data class Registro(
    val id: Int,
    val paquete_id: Int,
    val pago_id: String,
    val token: String,
    val usuario_id: Int,
    val regalo_id: Int,
    val usuario: Usuario?,
    val paquete: Paquete?
)
