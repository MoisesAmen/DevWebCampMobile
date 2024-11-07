package com.mss.appdevwebcampmovile_v2.repository

import com.mss.appdevwebcampmovile_v2.api.RetrofitClient
import com.mss.appdevwebcampmovile_v2.models.LoginRequest
import com.mss.appdevwebcampmovile_v2.models.LoginResponse
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

class AuthRepository {
    suspend fun login(email: String, password: String): Result<LoginResponse> {
        val loginRequest = LoginRequest(email, password)
        return try {
            val response = RetrofitClient.authApi.login(loginRequest)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Error de conexión"))
            } else {
                Result.failure(Exception("Error: ${response.errorBody()?.string()}"))
            }
        } catch (e: SocketTimeoutException) {
            Result.failure(Exception("No se pudo conectar al servidor. Por favor, inténtalo de nuevo más tarde."))
        } catch (e: IOException) {
            Result.failure(Exception("Hubo un problema con la conexión. Por favor, revisa tu conexión a internet."))
        } catch (e: Exception) {
            Result.failure(Exception("Ocurrió un error inesperado. Por favor, inténtalo de nuevo."))
        }
    }
}


/*class AuthRepository {
    suspend fun login(email: String, password: String): Response<LoginResponse> {
        val loginRequest = LoginRequest(email, password)
        return RetrofitClient.authApi.login(loginRequest)
    }
}*/
