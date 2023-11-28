package com.example.practica3.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practica3.AddPlayerScreen
import com.example.practica3.Main

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController =navController , startDestination = AppScreens.App.ruta ){
        composable(route = AppScreens.App.ruta){
            Main(navController)
        }
        composable(route = AppScreens.Add.ruta){
            AddPlayerScreen(navController)
        }
    }
}