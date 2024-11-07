package com.mss.appdevwebcampmovile_v2.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AdminViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AdminViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AdminViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

/*

class GenericViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AdminViewModel::class.java) -> {
                AdminViewModel(context) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(context) as T
            }
            // Añade más ViewModels aquí si es necesario
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}*/
