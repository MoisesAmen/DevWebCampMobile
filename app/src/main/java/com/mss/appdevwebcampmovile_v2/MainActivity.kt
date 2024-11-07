package com.mss.appdevwebcampmovile_v2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.background
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import kotlinx.coroutines.launch
import android.util.Log

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mss.appdevwebcampmovile_v2.views.LoginScreen
import androidx.compose.ui.*

import com.mss.appdevwebcampmovile_v2.api.RetrofitClient
import com.mss.appdevwebcampmovile_v2.ui.theme.AppDevWebCampMovile_v2Theme
import com.mss.appdevwebcampmovile_v2.models.Evento
import com.mss.appdevwebcampmovile_v2.views.AdminPonentes
import com.mss.appdevwebcampmovile_v2.views.AdminScreen
import com.mss.appdevwebcampmovile_v2.views.EventosScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppDevWebCampMovile_v2Theme {
                MaterialTheme {
                    AppNavigator()
                }
            }
        }
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("main") { EventosScreen(navController) }
        composable("admin") { AdminScreen(navController) }
    }
}






