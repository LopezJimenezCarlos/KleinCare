package com.lopezzzcut.kleincare

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lopezzzcut.kleincare.ui.theme.Poppins
import com.lopezzzcut.whatcut.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""
    val shape = RoundedCornerShape(24.dp)
    NavigationBar(tonalElevation = 12.dp,containerColor = Color.White,
        modifier = Modifier.clip(shape).width(360.dp)) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            BottomBarBoton(
                icono =  if (currentRoute == AppScreens.Inicio.route) R.drawable.homeiconfilled else R.drawable.homeicon,
                navController = navController,
                ruta = AppScreens.Inicio.route,
                modifier = Modifier.weight(1f),
            )
            BottomBarBoton(
                icono =  if (currentRoute == AppScreens.Pastillas.route) R.drawable.pilliconfilled else R.drawable.pillicon,
                navController = navController,
                ruta = AppScreens.Pastillas.route,
                modifier = Modifier.weight(1f),
            )
            BottomBarBoton(
                icono =  if (currentRoute == AppScreens.citas.route) R.drawable.citasiconfilled else R.drawable.citasicon,
                navController = navController,
                ruta = AppScreens.citas.route,
                modifier = Modifier.weight(1f),
            )
            BottomBarBoton(
                icono =  if (currentRoute == AppScreens.contactos.route) R.drawable.contacticonfilled else R.drawable.contacticon,
                navController = navController,
                ruta = AppScreens.contactos.route,
                modifier = Modifier.weight(1f),
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar2(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""
    val shape = RoundedCornerShape(24.dp)
    NavigationBar(tonalElevation = 12.dp,containerColor = Color.White,
        modifier = Modifier.clip(shape).width(360.dp)) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            BottomBarBoton(
                icono =  if (currentRoute == AppScreens.inicioAdmin.route) R.drawable.homeiconfilled else R.drawable.homeicon,
                navController = navController,
                ruta = AppScreens.inicioAdmin.route,
                modifier = Modifier.weight(1f),
            )
            BottomBarBoton(
                icono =  if (currentRoute == AppScreens.pastillasAdmin.route) R.drawable.pilliconfilled else R.drawable.pillicon,
                navController = navController,
                ruta = AppScreens.pastillasAdmin.route,
                modifier = Modifier.weight(1f),
            )
            BottomBarBoton(
                icono =  if (currentRoute == AppScreens.visitasAdmin.route) R.drawable.citasiconfilled else R.drawable.citasicon,
                navController = navController,
                ruta = AppScreens.visitasAdmin.route,
                modifier = Modifier.weight(1f),
            )
            BottomBarBoton(
                icono =  if (currentRoute == AppScreens.contactosAdmin.route) R.drawable.contacticonfilled else R.drawable.contacticon,
                navController = navController,
                ruta = AppScreens.contactosAdmin.route,
                modifier = Modifier.weight(1f),
            )
        }
    }
}


@Composable
fun BottomBarBoton(navController: NavController,
                   ruta:String,
                   icono:Int,
                   modifier: Modifier)
{
    IconButton(onClick = { navController.navigate(ruta) },
        modifier = modifier) {
        Image(
            painter = painterResource(id = icono),
            contentDescription = "Home",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    BottomBar(navController = rememberNavController())
}
