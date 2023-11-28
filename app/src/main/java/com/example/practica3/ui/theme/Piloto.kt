package com.example.practica3.ui.theme

import androidx.compose.ui.graphics.Color

data class Piloto(var nombre:String,
                  var equipo : String,
                  val puntos: Int,
                  val carrera: String,
                  val fecha: String,
                  val vueltaRapida: String,
                  val foto: Int,
                  val color: Color
) {

}