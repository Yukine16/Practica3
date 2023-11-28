package com.example.practica3

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.practica3.nav.AppScreens
import com.example.practica3.ui.theme.AlfaRomeo
import com.example.practica3.ui.theme.AlphaTauri
import com.example.practica3.ui.theme.Alpine
import com.example.practica3.ui.theme.AstonMartin
import com.example.practica3.ui.theme.Ferrari
import com.example.practica3.ui.theme.Hass
import com.example.practica3.ui.theme.McLaren
import com.example.practica3.ui.theme.Mercedes
import com.example.practica3.ui.theme.Piloto
import com.example.practica3.ui.theme.RedBull
import com.example.practica3.ui.theme.Williams


@Composable
fun PilotoRow(piloto: Piloto) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(piloto.color)
    ) {
        Image(
            painter = painterResource(id = piloto.foto),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(MaterialTheme.shapes.small)
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(text = piloto.nombre)
            Text(text = piloto.carrera)
            Text(text = piloto.fecha)
            Text(text = piloto.vueltaRapida)
        }
    }
}

@Composable
fun PilotoList(
    pilotos: List<Piloto>,
    searchTerm: String,
    onSearchTermChange: (String) -> Unit,
    navController: NavController
) {
    Column {
        SearchBar(
            searchTerm = searchTerm,
            onSearchTermChange = onSearchTermChange
        )
        LazyColumn(modifier = Modifier
            .weight(1f)
            .padding(16.dp)) {

            items(pilotos) { piloto ->
                if (piloto.nombre.contains(searchTerm, ignoreCase = true)) {
                    PilotoRow(piloto = piloto)
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ExtendedFloatingActionButton(
                text = { Text("Add") },
                onClick = { navController.navigate(route = AppScreens.Add.ruta) },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                },
                modifier = Modifier
                    .padding(end = 16.dp)
            )

            ExtendedFloatingActionButton(
                text = { Text("Delete") },
                onClick =  { },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = null
                    )
                },
                modifier = Modifier
                    .padding(start = 16.dp)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(searchTerm: String, onSearchTermChange: (String) -> Unit) {
    var isSearching by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextField(
                    value = searchTerm,
                    onValueChange = {
                        onSearchTermChange(it)
                        isSearching = it.isNotEmpty()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = if (isSearching) 0.dp else 16.dp),
                    label = { Text("Buscar pilotos por equipo") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    singleLine = true
                )

                if (isSearching) {
                    IconButton(
                        onClick = {
                            onSearchTermChange("")
                            isSearching = false
                        },
                        modifier = Modifier.align(Alignment.CenterVertically)
                    ) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                    }
                }
            }

            if (isSearching) {
                Divider(modifier = Modifier.padding(vertical = 4.dp))
            }
        }
    }
}


@Composable
fun Main(navController: NavController) {
    val listaPilotos = listOf(
        Piloto("Lewis Hamilton","Mercedes",25, "Gran Premio de España","2023-09-19", "1:30.456", R.drawable._0292c3d_91b2_4306_80a4_6765fa3c4931, Mercedes  ),
        Piloto("Max Verstappen", "Red Bull Racing", 18, "Gran Premio de México",  "2023-10-18",  "1:31.789", R.drawable.max_verstappen_you_are_the_world_champion,  RedBull ),
        Piloto("Fernando Alonso", "Aston Martin",15, "Gran Premio de Italia", "2023-04-17",  "1:32.123", R.drawable.nano_jpg, AstonMartin),
        Piloto("Valtteri Bottas", "Alfa Romeo",15,  "Gran Premio de Alemania", "2023-08-16","1:33.567", R.drawable._7f06a3b_9789_4db2_a0cd_c24970426062, AlfaRomeo),
        Piloto("Charles Leclerc", "Ferrari", 10, "Gran Premio de Holanda",  "2023-09-15","1:34.789", R.drawable._b1b6019_578b_4971_ba07_2691c449b999, Ferrari),
        Piloto("Lance Stroll", "Aston Martin",8,  "Gran Premio de Bélgica","2023-05-14",  "1:35.678", R.drawable.dff633ef_0224_4fd0_9ed1_65f63eb766d2, AstonMartin),
        Piloto("Lando Norris", "McLaren", 6,  "Gran Premio de COTA", "2023-07-13","1:36.543", R.drawable._dfcb9bc_f593_4e7e_a44b_84fd2d5861f6, McLaren),
        Piloto("Carlos Sainz", "Ferrari", 4,"Gran Premio de Emilia-Romaña",  "2023-04-12", "1:37.789", R.drawable.af323e21_362a_4426_bdf5_9122242ec0c1, Ferrari),
        Piloto("Sergio Pérez", "Red Bull Racing", 2, "Gran Premio de AbuDhabi",  "2023-06-11","1:38.890", R.drawable.saudi_arabia_gp_2023, RedBull),
        Piloto("Pierre Gastly", "Alpine", 8, "Gran Premio de Belgica",  "2023-03-06","1:14.364", R.drawable.pierre_gasly, Alpine),
        Piloto("George Rusell", "Mercedes", 4,"Gran Premio de Australia", "2023-09-10",  "1:12.465", R.drawable.george_russell, Mercedes),
        Piloto("Oscar Piastri", "McLaren", 2,"Gran Premio de Singapour",  "2023-11-02", "1:22.034", R.drawable.oscar, McLaren),
        Piloto("Esteban Ocon", "Alpine", 1, "Gran Premio de Canada",  "2023-05-26","1:33.012", R.drawable._57d7a88_c880_4ef0_bea8_76c09cc3e8eb, Alpine),
        Piloto("Yuki Tsunoda", "Alpha Tauri", 10, "Gran Premio de Monza",  "2023-03-10","1:10.213", R.drawable._4c765b9_627b_4e96_affc_90b9844e1320, AlphaTauri),
        Piloto("Nico Hulkenberg", "Hass", 8, "Gran Premio de Brazil",  "2023-09-13","1:24.098", R.drawable._e3e2a82_0bbe_4795_9b16_04c123ee162b, Hass),
        Piloto("Alex Albon", "Williams", 6,"Gran Premio de Qatar",  "2023-06-10", "1:12.034", R.drawable._419eae1_f2fa_4f1a_8ffe_986c04aa1db5, Williams),
        Piloto("Kevin Magunsen", "Hass", 2, "Gran Premio de Hungria",  "2023-04-23","1:25.634", R.drawable.e1ba4bb9_2bff_499e_b6aa_511bf7fa69ca, Hass),
        Piloto("Daniel Ricciardo", "Alpha Tauri", 0, "Gran Premio de Austria", "2023-08-14", "1:18.099", R.drawable.c89efe4e_2548_4995_a94c_f75cf175eaa3, AlphaTauri),
        Piloto("Logan Sargeant", "Williams", 1,"Gran Premio de Monaco",  "2023-05-09", "1:36.089", R.drawable.e5ceb17e_7aeb_43a8_9d90_e2e9beffaf83, Williams),
        Piloto("Guanyu Zhou", "Alfa Romeo", 1, "Gran Premio de Azerbaijan", "2023-07-10", "1:16.123", R.drawable._6b420db_c424_4384_9399_cd28c5d0a768, AlfaRomeo),


        )
    PilotoList(
        pilotos = listaPilotos,
        searchTerm = "",
        onSearchTermChange = {},
        navController = navController
    )
}
