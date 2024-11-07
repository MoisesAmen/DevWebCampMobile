package com.mss.appdevwebcampmovile_v2.repository

import com.mss.appdevwebcampmovile_v2.api.RetrofitClient
import com.mss.appdevwebcampmovile_v2.models.Dashboard
import com.mss.appdevwebcampmovile_v2.models.Regalo
import com.mss.appdevwebcampmovile_v2.models.Registro
import retrofit2.Response

class AdminRepository {
    suspend fun getDashboardData(token: String): Response<Dashboard> {
        return RetrofitClient.adminApi.getDashboardData(token)
    }

    suspend fun getRegistradosData(token: String): List<Registro> {
        return RetrofitClient.adminApi.getRegistrados(token)
    }

    suspend fun getRegalosData(token: String): List<Regalo> {
        return RetrofitClient.adminApi.getRegalos(token)
    }

}