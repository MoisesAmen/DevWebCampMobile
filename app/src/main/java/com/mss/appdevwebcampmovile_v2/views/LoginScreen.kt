package com.mss.appdevwebcampmovile_v2.views

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavController
import com.mss.appdevwebcampmovile_v2.R
import com.mss.appdevwebcampmovile_v2.repository.AuthRepository
import com.mss.appdevwebcampmovile_v2.ui.theme.Principal
import com.mss.appdevwebcampmovile_v2.ui.theme.Secundario
import com.mss.appdevwebcampmovile_v2.viewModels.LoginViewModel
import com.mss.appdevwebcampmovile_v2.viewModels.LoginViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModelFactory(context))
    val email by loginViewModel.email
    val password by loginViewModel.password
    val errorMessage by loginViewModel.errorMessage
    val isEmailFocused by loginViewModel.isEmailFocused
    val isPasswordFocused by loginViewModel.isPasswordFocused

    val gradientBrush = Brush.linearGradient(
        colors = listOf(Principal, Secundario) // Replace with your desired colors
    )

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.header),
                contentDescription = "Background Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.size(400.dp)) {
                    drawCircle(
                        color = Color.Black.copy(alpha = 0.5f),
                        radius = size.minDimension / 2
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "<DevWebCamp />",
                fontSize = 42.sp, // Adjust as needed
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                style = MaterialTheme.typography.titleLarge.copy(
                    brush = gradientBrush,
                    textAlign = TextAlign.Center // Usar TextAlign.Start para alineación a la izquierda
                )
            )

            Text(
                text = "Iniciar sesión",
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            TextField(
                value = email,
                onValueChange = { loginViewModel.email.value = it },
                label = {
                    Text(
                        "Email",
                        color = if (isEmailFocused) Principal else Color.Gray // Cambia los colores según necesites
                    )
                },
                modifier = Modifier
                    .padding(8.dp)
                    .onFocusChanged { focusState -> loginViewModel.isEmailFocused.value = focusState.isFocused },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Principal, // Color de la línea cuando está enfocado
                    cursorColor = Principal // Color de la barra de escritura
                )
            )

            Spacer(modifier = Modifier.height(6.dp))

            TextField(
                value = password,
                onValueChange = { loginViewModel.password.value = it },
                label = {
                    Text(
                        "Password",
                        color = if (isPasswordFocused) Principal else Color.Gray // Cambia los colores según necesites
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .padding(8.dp)
                    .onFocusChanged { focusState -> loginViewModel.isPasswordFocused.value = focusState.isFocused },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Principal, // Color de la línea cuando está enfocado
                    cursorColor = Principal // Color de la barra de escritura
                )
            )

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    loginViewModel.login(navController)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Principal), // Change button color here
                modifier = Modifier
                    .padding(16.dp)
                    .size(width = 200.dp, height = 50.dp),
            ) {
                Text("Iniciar sesión", color = Color.White, style = MaterialTheme.typography.bodyLarge)
            }

            errorMessage?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Red, shape = RoundedCornerShape(8.dp))
                        .padding(8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = "Error",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = it,
                            color = Color.White,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}


