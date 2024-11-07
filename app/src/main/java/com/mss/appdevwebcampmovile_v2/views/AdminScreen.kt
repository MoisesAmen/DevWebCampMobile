package com.mss.appdevwebcampmovile_v2.views

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mss.appdevwebcampmovile_v2.R
import com.mss.appdevwebcampmovile_v2.models.Dashboard
import com.mss.appdevwebcampmovile_v2.repository.AuthRepository
import com.mss.appdevwebcampmovile_v2.ui.theme.grisClaro
import com.mss.appdevwebcampmovile_v2.ui.theme.grisOscuro
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch



@Composable
fun AdminScreen(navController: NavController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val adminNavController = rememberNavController() // Sub NavController para las rutas de administración

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(adminNavController, navController, scope, drawerState) // Pasar ambos NavControllers
        }
    ) {
        Column {
            AdminHeader(scope, drawerState)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                // Aquí se define el AdminNavigator con admin_home como ruta de inicio
                AdminNavigator(adminNavController)
            }
        }
    }
}

@Composable
fun AdminHeader(scope: CoroutineScope, drawerState: DrawerState) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(grisOscuro)
            .padding(top = 40.dp, start = 20.dp, bottom = 20.dp, end = 20.dp)
    ) {
        Row(
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            IconButton(
                onClick = {
                    scope.launch { drawerState.open() }
                }
            ) {
                Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White, modifier = Modifier.size(36.dp))
            }
        }

        Text(
            text = "<DevWebCamp />",
            fontSize = 34.sp,
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.Center)
        )
    }

}

@Composable
fun DrawerContent(
    adminNavController: NavController,
    mainNavController: NavController,
    scope: CoroutineScope, // Recibe el scope de corrutina
    drawerState: DrawerState // Recibe el estado del drawer
) {
    // Definimos `menuItems` como una lista de `Triple`, lo que permite una desestructuración sencilla
    val menuItems = listOf(
        Triple("INICIO", Icons.Default.Home, "admin_home"),
        Triple("PONENTES", Icons.Default.Person, "admin_ponentes"),
        Triple("EVENTOS", Icons.Default.DateRange, "admin_eventos"),
        Triple("REGISTRADOS", Icons.Default.Face, "admin_registrados"),
        Triple("REGALOS", Icons.Default.Info, "admin_regalos")
    )

    // Obtiene la ruta actual del NavController
    val navBackStackEntry by adminNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    //println("RutaOriginal: $currentRoute")
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.5f) // Ocupa la mitad del ancho de la pantalla
            .background(grisOscuro)
            .padding(vertical = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween, // Distribuye los elementos con espacio entre ellos
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Parte superior: Título o Logo (opcional)
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            /*Text(
                text = "<DevWebCamp />",
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleLarge
            )*/

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "DevWebCamp Logo",
                modifier = Modifier.padding(16.dp)
            )

        }

        // Elementos del menú
        Column {
            menuItems.forEach { (label, icon, route) ->
                val isSelected = currentRoute == route
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(if (isSelected) grisClaro else Color.Transparent) // Fondo para el seleccionado
                        .clickable {
                            if (!isSelected) {
                                adminNavController.navigate(route) {
                                    popUpTo(adminNavController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                    //println("Ruta: $currentRoute")
                                }
                            }
                            // Cierra el drawer después de la navegación
                            scope.launch { drawerState.close() }
                        }
                        .padding(vertical = 12.dp, horizontal = 16.dp),
                    //verticalAlignment = Alignment.CenterVertically
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        tint = Color.White,
                        modifier = Modifier
                            .size(35.dp)
                    )
                    Text(
                        text = label,
                        color = Color.White,
                        style = if (isSelected) MaterialTheme.typography.labelMedium else MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(top = 5.dp)
                    )
                }
            }
        }

        // Botón de cierre de sesión
        Button(
            onClick = {
                mainNavController.navigate("login") {
                    popUpTo("admin") { inclusive = true } // Vuelve a la pantalla de login al cerrar sesión
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Cerrar Sesión", color = Color.White)
        }
    }
}


@Composable
fun AdminNavigator(adminNavController: NavHostController) {
    val context = LocalContext.current
    NavHost(navController = adminNavController, startDestination = "admin_home") {
        composable("admin_home") { AdminHome(context) }
        composable("admin_ponentes") { AdminPonentes() }
        composable("admin_eventos") { AdminEventos() }
        composable("admin_registrados") { AdminRegistrados(context) }
        composable("admin_regalos") { AdminRegalos(context) }
    }
}