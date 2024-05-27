package com.lopezzzcut.whatcut.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.lopezzzcut.kleincare.screens.ContactosFavoritos
import com.lopezzzcut.kleincare.screens.Inicio
import com.lopezzzcut.kleincare.screens.Pastillas
import com.lopezzzcut.kleincare.screens.Visitas
import com.lopezzzcut.kleincare.screens.Welcome
import com.lopezzzcut.kleincare.screens.splash
import com.lopezzzcut.kleincare.screens.splashscreen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.Splash.route) {
        composable(AppScreens.Splash.route) {
            splashscreen(navController = navController, context = LocalContext.current)
        }
        composable(AppScreens.Welcome.route) {
            Welcome(navController = navController, context = LocalContext.current)
        }
        composable(AppScreens.Inicio.route) {
            Inicio(navController = navController, context = LocalContext.current)
        }

        composable(AppScreens.Pastillas.route) {
            Pastillas(navController = navController, context = LocalContext.current)
        }
        composable(AppScreens.citas.route) {
            Visitas(navController = navController, context = LocalContext.current)
        }
        composable(AppScreens.contactos.route) {
            ContactosFavoritos(navController = navController, context = LocalContext.current)
        }

    }

}