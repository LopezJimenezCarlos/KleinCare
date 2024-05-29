package com.lopezzzcut.whatcut.navigation

sealed class AppScreens(val route: String) {



    object Splash: AppScreens("splashscreen")
    object Welcome: AppScreens("welcome")

    object Inicio: AppScreens("inicio")
    object Pastillas: AppScreens("pastillas")
    object citas: AppScreens("citas")
    object contactos: AppScreens("contactos")
    object inicioAdmin: AppScreens("inicioAdmin")
    object pastillasAdmin: AppScreens("pastillasAdmin")
}
