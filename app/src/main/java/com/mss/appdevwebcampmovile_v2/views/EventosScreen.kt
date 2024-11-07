package com.mss.appdevwebcampmovile_v2.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mss.appdevwebcampmovile_v2.api.RetrofitClient
import com.mss.appdevwebcampmovile_v2.models.Evento
import com.mss.appdevwebcampmovile_v2.repository.AdminRepository
import com.mss.appdevwebcampmovile_v2.repository.EventoRepository
import com.mss.appdevwebcampmovile_v2.ui.theme.Principal
import com.mss.appdevwebcampmovile_v2.ui.theme.Secundario
import kotlinx.coroutines.launch

@Composable
fun EventosScreen(navController: NavController) {

    val gradientBrush = Brush.linearGradient(
        colors = listOf(Principal, Secundario) // Replace with your desired colors
    )

    // Usar un único LazyColumn para que toda la pantalla sea desplazable
    LazyColumn(modifier = Modifier.fillMaxSize().padding(vertical = 50.dp, horizontal = 16.dp)) {
        // Título y Descripción
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
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
                    text = "Workshops & Conferencias",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp),
                    color = Color(0xFF007DF4)
                )
                Text(
                    text = "Talleres y Conferencias dictados por expertos en desarrollo web",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }

        // Espacio entre secciones
        item { Spacer(modifier = Modifier.height(16.dp)) }

        // Título y lista de Conferencias
        item {
            Text(text = "<Conferencias />", style = MaterialTheme.typography.headlineSmall, color = Principal)
            Spacer(modifier = Modifier.height(10.dp))
            EventosPorDiaFiltrados("Jueves 25 de Julio", "1", "1", color = Principal)
            Spacer(modifier = Modifier.height(10.dp))
            EventosPorDiaFiltrados("Viernes 26 de Julio", "1", "2",color = Principal)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "<Workshops />", style = MaterialTheme.typography.headlineSmall, color = Secundario)
            Spacer(modifier = Modifier.height(10.dp))
            EventosPorDiaFiltrados("Jueves 25 de Julio", "2", "1",color = Secundario)
            Spacer(modifier = Modifier.height(10.dp))
            EventosPorDiaFiltrados("Viernes 26 de Julio", "2", "2", color = Secundario)
        }

        // Espacio entre secciones
        item { Spacer(modifier = Modifier.height(16.dp)) }

        item{
            Button(
                onClick = {
                    // Aquí puedes limpiar cualquier estado de autenticación si es necesario
                    navController.navigate("login") {
                        popUpTo("main") { inclusive = true }
                    }
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Cerrar Sesión")
            }
        }


    }
}



@Composable
fun EventoItem(evento: Evento, color: Color) {
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 5.dp)
            .width(280.dp)
    ) {

        // Hora en la parte superior derecha
        Text(
            text = evento.hora.hora,
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(end = 8.dp, top = 8.dp) // Agrega algo de padding para que no esté pegado al borde
        )


        Spacer(modifier = Modifier.height(10.dp))

        // Contenedor principal con color de fondo y bordes redondeados
        Column(
            modifier = Modifier
                .background(color = color, shape = RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            // Nombre del evento
            Text(
                text = evento.nombre.take(25) + if (evento.nombre.length > 50) "..." else "",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold, // Negrita
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Descripción truncada a 50 caracteres
            Text(
                text = evento.descripcion.take(50) + if (evento.descripcion.length > 50) "..." else "",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Ponente en la parte inferior izquierda
            Text(
                text = "${evento.ponente.nombre} ${evento.ponente.apellido}",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold, // Negrita
                modifier = Modifier.align(Alignment.End),
                color = Color.White
            )
        }
    }
}

@Composable
fun EventosPorDia(fecha: String, categoriaId: String, diaId: String, color: Color) {
    val eventos = remember { mutableStateListOf<Evento>() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            try {
                val response = RetrofitClient.eventoApi.listarEventos()
                //eventos.addAll(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    Column {
        Text(text = fecha, style = MaterialTheme.typography.bodySmall)

        // Slider para la lista de eventos
        LazyRow(modifier = Modifier.padding(vertical = 8.dp)) {
            items(eventos.filter { it.categoria.id == categoriaId && it.dia.id == diaId }
                .sortedBy { it.hora.hora }) { evento ->
                EventoItem(evento, color = color)
            }
        }
    }
}

@Composable
fun EventosPorDiaFiltrados(fecha: String, categoriaId: String, diaId: String, color: Color) {
    val eventos = remember { mutableStateListOf<Evento>() }
    val scope = rememberCoroutineScope()
    val eventoRepository = EventoRepository()

    LaunchedEffect(Unit) {
        scope.launch {
            try {
                val response = eventoRepository.getEventos()
                val key = "evento_${categoriaId}_${diaId}"
                response[key]?.let {
                    eventos.addAll(it)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    Column {
        Text(text = fecha, style = MaterialTheme.typography.bodySmall)

        // Slider para la lista de eventos
        LazyRow(modifier = Modifier.padding(vertical = 8.dp)) {
            items(eventos.sortedBy { it.hora.hora }) { evento ->
                EventoItem(evento, color = color)
            }
        }
    }
}