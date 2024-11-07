package com.mss.appdevwebcampmovile_v2.api

import com.mss.appdevwebcampmovile_v2.models.LoginRequest
import com.mss.appdevwebcampmovile_v2.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("api/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}