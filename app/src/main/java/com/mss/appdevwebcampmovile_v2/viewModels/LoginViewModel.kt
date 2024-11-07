package com.mss.appdevwebcampmovile_v2.viewModels

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.mss.appdevwebcampmovile_v2.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val context: Context) : ViewModel() {
    private val authRepository = AuthRepository()

    var email = mutableStateOf("")
    var password = mutableStateOf("")
    var errorMessage = mutableStateOf<String?>(null)
    var isEmailFocused = mutableStateOf(false)
    var isPasswordFocused = mutableStateOf(false)

    fun login(navController: NavController) {
        viewModelScope.launch {
            val result = authRepository.login(email.value, password.value)
            result.onSuccess { loginResponse ->
                val token = loginResponse.token ?: ""
                // Guarda el token en SharedPreferences
                val sharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
                sharedPreferences.edit().putString("auth_token", token).apply()

                val admin = loginResponse.user?.admin ?: 0
                if (admin == 1) {
                    navController.navigate("admin") {
                        popUpTo("login") { inclusive = true }
                    }
                } else {
                    navController.navigate("main") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            }.onFailure { exception ->
                errorMessage.value = exception.message
                showAlert(exception.message ?: "Error desconocido")
            }
        }
    }

    private fun showAlert(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()

        AlertDialog.Builder(context)
            .setTitle("Alerta")
            .setMessage(message)
            .setPositiveButton(android.R.string.ok, null)
            .show()
    }
}
