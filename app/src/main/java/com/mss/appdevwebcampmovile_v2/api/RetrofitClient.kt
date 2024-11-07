package com.mss.appdevwebcampmovile_v2.api

import android.os.Build
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val BASE_URL = "http://192.168.1.4:3200/"

        /*if (isEmulator()) {
        "http://10.0.2.2:3200/"
    } else {
        "http://127.0.0.1:3200/" // Cambia esta IP a la IP de tu computadora
    }*/

    // Método para detectar si está en un emulador
    /*private fun isEmulator(): Boolean {
        return Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion") || Build.BRAND.startsWith("generic")
                && Build.DEVICE.startsWith("generic") || "google_sdk" == Build.PRODUCT
    }*/

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Interfaces API
    val eventoApi: EventoApiService by lazy {
        retrofit.create(EventoApiService::class.java)
    }

    val authApi: AuthApiService by lazy {
        retrofit.create(AuthApiService::class.java)
    }

    val adminApi: AdminApiService by lazy {
        retrofit.create(AdminApiService::class.java)
    }
}
