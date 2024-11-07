package com.mss.appdevwebcampmovile_v2.viewModels

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mss.appdevwebcampmovile_v2.models.Dashboard
import com.mss.appdevwebcampmovile_v2.models.Regalo
import com.mss.appdevwebcampmovile_v2.models.Registro
import com.mss.appdevwebcampmovile_v2.repository.AdminRepository
import kotlinx.coroutines.launch

class AdminViewModel(context: Context) : ViewModel() {
    private val adminRepository = AdminRepository()
    var dashboardData = mutableStateOf<Dashboard?>(null)
    var registrosData = mutableStateOf<List<Registro>>(emptyList())
    var regaloData = mutableStateOf<List<Regalo>>(emptyList())
    var errorMessage = mutableStateOf("")

    private val sharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
    private val token = sharedPreferences.getString("auth_token", "") ?: ""

    init {
        fetchDashboardData()
        fetchRegistrosData()
        fetchRegalosData()
    }

    private fun fetchDashboardData() {
        viewModelScope.launch {
            try {
                val response = adminRepository.getDashboardData(token)
                if (response.isSuccessful) {
                    dashboardData.value = response.body()
                } else {
                    errorMessage.value = "Error al cargar los datos del panel"
                }
            } catch (e: Exception) {
                errorMessage.value = "Error fetching data: ${e.message}"
            }
        }
    }

    private fun fetchRegistrosData() {
        viewModelScope.launch {
            try {
                val response = adminRepository.getRegistradosData(token)
                if (response.isNotEmpty()) {
                    registrosData.value = response
                } else {
                    errorMessage.value = "No data available or failed to fetch data"
                }
            } catch (e: Exception) {
                errorMessage.value = "Error fetching data: ${e.message}"
            }
        }
    }

    private fun fetchRegalosData() {
        viewModelScope.launch {
            try {
                val response = adminRepository.getRegalosData(token)
                if (response.isNotEmpty()) {
                    regaloData.value = response
                } else {
                    errorMessage.value = "No data available or failed to fetch data"
                }
            } catch (e: Exception) {
                errorMessage.value = "Error fetching data: ${e.message}"
            }
        }
    }
}