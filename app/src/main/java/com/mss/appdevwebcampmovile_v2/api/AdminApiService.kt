package com.mss.appdevwebcampmovile_v2.api

import com.mss.appdevwebcampmovile_v2.models.Dashboard
import com.mss.appdevwebcampmovile_v2.models.Regalo
import com.mss.appdevwebcampmovile_v2.models.Registro
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface AdminApiService {

    @GET("api/admin/dashboard")
    suspend fun getDashboardData(@Header("Authorization") token: String): Response<Dashboard>

    /*@GET("api/admin/dashboard")
    suspend fun getDashboardData(): Response<Dashboard>*/

    @GET("api/admin/registrados")
    suspend fun getRegistrados(@Header("Authorization") token: String): List<Registro>


    @GET("api/admin/regalos")
    suspend fun getRegalos(@Header("Authorization") token: String): List<Regalo>
}