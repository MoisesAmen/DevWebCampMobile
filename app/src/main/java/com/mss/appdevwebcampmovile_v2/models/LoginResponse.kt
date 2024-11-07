package com.mss.appdevwebcampmovile_v2.models

data class LoginResponse(
    val success: Boolean,
    val token: String?,
    val user: Usuario?,
    val message: String?
)