package com.example.practica3



import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.time.Instant
import java.time.ZoneId


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPlayerScreen(navController: NavController
) {
    var expandedPiloto by remember {mutableStateOf(false)}
    var expandedCarrera by remember {mutableStateOf(false)}
    var expandedEquipo by remember {mutableStateOf(false)}
    val pilotos = listOf("Lewis Hamilton", "Max Verstappen", "Fernando Alonso", "Carlos Sainz", "Valtteri Bottas","Charles Leclerc","Lance Stroll","Lando Norris","Sergio Pérez","Pierre Gastly","George Rusell","Oscar Piastri","Esteban Ocon","Yuki Tsunoda","Nico Hulkenberg","Alex Albon","Kevin Magunsen","Daniel Ricciardo","Logan Sargeant", "Guanyu Zhou")
    val equipos = listOf("Mercedes", "RedBull", "Aston Martin", "Alfa Romeo","Ferrari", "McLaren","Alpine","Alpha Tauri","Hass","Williams")
    val carreras = listOf(
        "Gran Premio de Australia",
        "Gran Premio de Baréin",
        "Gran Premio de Brazil",
        "Gran Premio de Azerbaiyán",
        "Gran Premio de España",
        "Gran Premio de Arabia Saudi",
        "Gran Premio de Miami",
        "Gran Premio de Emilia-Romagna",
        "Gran Premio de Monaco",
        "Gran Premio de Canada",
        "Gran Premio de Austria",
        "Gran Premio de Reino Unido",
        "Gran Premio de Hungria",
        "Gran Premio de Belgica",
        "Gran Premio de Holanda",
        "Gran Premio de Monza",
        "Gran Premio de Singapour",
        "Gran Premio de Japon",
        "Gran Premio de Qatar",
        "Gran Premio de COTA",
        "Gran Premio de Mexico",
        "Gran Premio de Las Vegas",
        "Gran Premio de Abu Dhabi"



    )
    var seleccionPiloto by remember { mutableStateOf(pilotos[0])}
    var seleccionEquipo by remember { mutableStateOf(equipos[0])}
    var seleccionCarrera by remember { mutableStateOf(carreras[0])}
    var seleccionPuntos by remember { mutableStateOf(0)}
    var selectedFastestLap by remember { mutableStateOf(0F) }
    var state = rememberDatePickerState()

    LazyColumn(modifier = Modifier
        .padding(16.dp)) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Row {
                    Text(text = "Seleccione el nombre del piloto")
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .clickable { expandedPiloto = true }
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(seleccionPiloto)

                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    DropdownMenu(
                        expanded = expandedPiloto,
                        onDismissRequest = { expandedPiloto = false }
                    ) {
                        pilotos.forEach { label ->
                            DropdownMenuItem(
                                text = { Text(text = label) },
                                onClick = {
                                    seleccionPiloto = label
                                    expandedPiloto = false
                                }
                            )
                        }
                    }
                }
            }
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Row {
                    Text(text = "Seleccione el equipo del piloto")
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .clickable { expandedEquipo = true }
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(seleccionEquipo)

                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    DropdownMenu(
                        expanded = expandedEquipo,
                        onDismissRequest = { expandedEquipo = false }
                    ) {
                        equipos.forEach { label ->
                            DropdownMenuItem(
                                text = { Text(text = label) },
                                onClick = {
                                    seleccionEquipo = label
                                    expandedEquipo = false
                                }
                            )
                        }
                    }
                }
            }
        }
        item { Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp)
        ) {
            RadioGroup(
                options = listOf(25, 18, 15, 12, 10, 8, 6, 4, 2, 1, 0),
                selectedOption = seleccionPuntos,
                onOptionSelected = { seleccionPuntos = it }
            )
        }
        }
        item {
            Slider(
                value = selectedFastestLap,
                onValueChange = { selectedFastestLap = it },
                onValueChangeFinished = {

                },
                steps = 50,
                valueRange = 100F..140F
            )
            Text(text = selectedFastestLap.toString())
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Row {
                    Text(text = "Seleccione la carrera")
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .clickable { expandedCarrera = true }
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(seleccionCarrera)

                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    DropdownMenu(
                        expanded = expandedCarrera,
                        onDismissRequest = { expandedCarrera = false }
                    ) {
                        carreras.forEach { label ->
                            DropdownMenuItem(
                                text = { Text(text = label) },
                                onClick = {
                                    seleccionCarrera = label
                                    expandedCarrera = false
                                }
                            )
                        }
                    }
                }
            }
        }
        item {
            Row {
                Text(text = "Seleccione la fecha de la carrera")
            }
            DatePicker(state = state )
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Button(onClick = {
                    // val nuevoPiloto = Piloto(
                    //nombre = seleccionPiloto,
                    // equipo = seleccionEquipo,
                    //fecha = ,
                    //carrera = seleccionCarrera,
                    // puntos = seleccionPuntos,
                    // vueltaRapida = selectedFastestLap.toString()

                    navController.popBackStack()}) {
                    Text(text = "Agregar jugador")
                }


            }
        }
    }

}


@Composable
fun RadioGroup(
    options: List<Int>,
    selectedOption: Int,
    onOptionSelected: (Int) -> Unit
) {
    Column {
        options.forEach { option ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOptionSelected(option) }
                    .padding(6.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = option == selectedOption,
                    onClick = { onOptionSelected(option) }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Puntos: $option")
            }
        }
    }
}
