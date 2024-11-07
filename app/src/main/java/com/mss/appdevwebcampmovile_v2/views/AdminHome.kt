package com.mss.appdevwebcampmovile_v2.views

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mss.appdevwebcampmovile_v2.models.Dashboard
import com.mss.appdevwebcampmovile_v2.models.Evento
import com.mss.appdevwebcampmovile_v2.repository.AdminRepository
import com.mss.appdevwebcampmovile_v2.ui.theme.Principal
import com.mss.appdevwebcampmovile_v2.ui.theme.Secundario
import com.mss.appdevwebcampmovile_v2.viewModels.AdminViewModel
import com.mss.appdevwebcampmovile_v2.viewModels.AdminViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun AdminHome(context: Context) {
    val adminViewModel: AdminViewModel = viewModel(factory = AdminViewModelFactory(context))
    val dashboardData by adminViewModel.dashboardData
    val errorMessage by adminViewModel.errorMessage

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        item {
            Text(
                "Panel de Administración",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        dashboardData?.let {
            item {
                AdminSummaryCard(dashboardData = it)
                Spacer(modifier = Modifier.height(16.dp))
                Divider()
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                SectionTitle("Eventos con Menos Lugares Disponibles")
                it.menos_disponibles.forEach { evento ->
                    EventItem(evento)
                }
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                SectionTitle("Eventos con Más Lugares Disponibles")
                it.mas_disponibles.forEach { evento ->
                    EventItem(evento)
                }
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                SectionTitle("Últimos Registros")
                it.registros.forEach { registro ->
                    Text("${registro.nombre} ${registro.apellido}", style = MaterialTheme.typography.bodyLarge)
                }
            }
        } ?: run {
            if (errorMessage.isNotEmpty()) {
                item {
                    Text(
                        errorMessage,
                        color = Color.Red,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun AdminSummaryCard(dashboardData: Dashboard) {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(Secundario, Principal) // Replace with your desired colors
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black, shape = RoundedCornerShape(16.dp)),
        //elevation = 4.dp,
        shape = RoundedCornerShape(16.dp) // Added border radius
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize() // Ensure the column fills the card
                .background(gradientBrush) // Apply the gradient background to the column
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Ingresos Virtuales: ${dashboardData.ingVirtuales}",
                color = Color.White,
                modifier = Modifier
                    .padding(5.dp)
            )
            Text("Ingresos Presenciales: ${dashboardData.ingPresenciales}",
                color = Color.White,
                modifier = Modifier
                    .padding(5.dp)
            )
            Text(
                "Ingresos Totales:",
                style = MaterialTheme.typography.labelMedium,
                color = Color.White,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
            Text(
                "${dashboardData.ingresos}",
                style = MaterialTheme.typography.labelMedium.copy(fontSize = 40.sp),
                color = Color.White,
                modifier = Modifier
                    .padding(5.dp)
            )
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
    }

}

@Composable
fun EventItem(evento: Evento) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.weight(0.9f)) {
                Text(
                    text = evento.nombre,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Box(modifier = Modifier.weight(0.1f)) {
                Text(
                    text = evento.disponibles,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    //modifier = Modifier
                    //    .padding(5.dp)
                )
            }
        }
    }
}