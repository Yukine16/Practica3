package com.example.practica3.nav

sealed class AppScreens(val ruta: String){
    object App: AppScreens("primera pantalla")
    object Add: AppScreens("segunda pantalla")
}
