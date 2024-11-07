package com.mss.appdevwebcampmovile_v2.views

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mss.appdevwebcampmovile_v2.models.Registro
import com.mss.appdevwebcampmovile_v2.ui.theme.Principal
import com.mss.appdevwebcampmovile_v2.viewModels.AdminViewModel
import com.mss.appdevwebcampmovile_v2.viewModels.AdminViewModelFactory

@Composable
fun AdminRegistrados(context: Context) {
    val adminViewModel: AdminViewModel = viewModel(factory = AdminViewModelFactory(context))
    val registros by adminViewModel.registrosData
    val errorMessage by adminViewModel.errorMessage

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            //.background(Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Usuarios Registrados",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Principal)
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Nombre",
                modifier = Modifier.weight(1f),
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Email",
                modifier = Modifier.weight(1f),
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Plan",
                modifier = Modifier.weight(1f),
                color = Color.White,
                textAlign = TextAlign.Center )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(registros) { registro ->
                RegistroItem(registro)
            }
        }
    }
}

@Composable
fun RegistroItem(registro: Registro) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant),
            //.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = registro.usuario?.nombre ?: "N/A",
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
        Text(
            text = registro.usuario?.email ?: "N/A",
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
        Text(
            text = registro.paquete?.nombre ?: "N/A",
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}
