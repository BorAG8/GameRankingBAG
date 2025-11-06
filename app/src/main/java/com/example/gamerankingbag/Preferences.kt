package com.example.gamerankingbag

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamerankingbag.ui.theme.Alternativo
import com.example.gamerankingbag.ui.theme.GameRankingPersonalTheme

class PreferencesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameRankingPersonalTheme {
                Preferencias()
            }
        }
    }
}

@Composable
fun Preferencias() {

    var rpg by remember { mutableStateOf(false) }
    var hack by remember { mutableStateOf(false) }
    var cars by remember { mutableStateOf(false) }
    var casual by remember { mutableStateOf(false) }
    var action by remember { mutableStateOf(false) }
    var platforms by remember { mutableStateOf(false) }
    var strategy by remember { mutableStateOf(false) }
    var graphic by remember { mutableStateOf(false) }
    var arcade by remember { mutableStateOf(false) }
    var fight by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(30.dp, 50.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            // Titulo plataformas
            Text(
                text = "Platforms:",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = CourgetteFontFamily,
                color = Alternativo
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
            ) {
                val showMessage: (String) -> Unit = { platform ->
                    Toast.makeText(
                        context,
                        "You have selected $platform",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                BotonSeleccionable("PS5", activado = showMessage)
                BotonSeleccionable("Xbox X/S", activado = showMessage)
                BotonSeleccionable("Switch", activado = showMessage)
                BotonSeleccionable("PC", activado = showMessage)
                BotonSeleccionable("Android", activado = showMessage)
                BotonSeleccionable("Apple", activado = showMessage)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Titulo tipos juego
            Text(
                text = "Games Type:",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = CourgetteFontFamily,
                color = Alternativo
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Checks categorias
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = rpg, onCheckedChange = { rpg = it })
                Text("RPG")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = hack, onCheckedChange = { hack = it })
                Text("Hack'n slash")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = cars, onCheckedChange = { cars = it })
                Text("Cars")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = casual, onCheckedChange = { casual = it })
                Text("Casual games")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = action, onCheckedChange = { action = it })
                Text("Action")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = platforms, onCheckedChange = { platforms = it })
                Text("Platforms")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = strategy, onCheckedChange = { strategy = it })
                Text("Strategy")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = graphic, onCheckedChange = { graphic = it })
                Text("Graphic adventure")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = arcade, onCheckedChange = { arcade = it })
                Text("Arcade")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = fight, onCheckedChange = { fight = it })
                Text("Fight")
            }

            Spacer(modifier = Modifier.height(24.dp))

            //Boton FAB
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                FloatingActionButton(
                    onClick = {
                        val selectedOptions = mutableListOf<String>()
                        if (rpg) selectedOptions.add("RPG")
                        if (hack) selectedOptions.add("Hack'n slash")
                        if (cars) selectedOptions.add("Cars")
                        if (casual) selectedOptions.add("Casual games")
                        if (action) selectedOptions.add("Action")
                        if (platforms) selectedOptions.add("Platforms")
                        if (strategy) selectedOptions.add("Strategy")
                        if (graphic) selectedOptions.add("Graphic adventure")
                        if (arcade) selectedOptions.add("Arcade")
                        if (fight) selectedOptions.add("Fight")

                        if (selectedOptions.isEmpty()) {
                            Toast.makeText(context, "Nothing selected", Toast.LENGTH_SHORT).show()
                        } else {
                            val message = separadores(selectedOptions)
                            Toast.makeText(
                                context,
                                "Adding to your profile: $message",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    },
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Text("Add your preferences")
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Elige una opción:",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            var selectedOption by remember { mutableStateOf("") }
            val options = listOf(
                "Angry Birds",
                "Dragon fly",
                "Hill climbing racing",
                "Pocket soccer",
                "Radiant defense"
            )

            options.forEach { option ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { selectedOption = option }
                        .padding(vertical = 4.dp)
                ) {
                    RadioButton(
                        selected = (selectedOption == option),
                        onClick = { selectedOption = option },
                        colors = RadioButtonDefaults.colors(selectedColor = Alternativo)
                    )
                    Text(text = option)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Valora tu juego:",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            var rating by remember { mutableStateOf(0) }

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                for (i in 1..5) {
                    Icon(
                        painter = painterResource(
                            id = if (i <= rating) R.drawable.star else R.drawable.staroutline
                        ),
                        contentDescription = "Star $i",
                        tint = if (i <= rating) Color.Black else Color.LightGray,
                        modifier = Modifier
                            .size(36.dp)
                            .clickable { rating = i }
                            .padding(4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            var sliderValue by remember { mutableStateOf(0f) }

            Slider(
                value = sliderValue,
                onValueChange = { sliderValue = it },
                valueRange = 0f..10f,
                steps = 9,
                colors = SliderDefaults.colors(
                    thumbColor = Alternativo,
                    activeTrackColor = Alternativo
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

//Apariencia botones según selección
@Composable
fun BotonSeleccionable(
    text: String,
    activado: (String) -> Unit
) {
    var seleccionado by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .padding(4.dp)
            .height(40.dp),
        shape = RoundedCornerShape(5.dp),
        color = if (seleccionado) Color(0xFF00796B) else Color.White,
        onClick = {
            seleccionado = !seleccionado
            if (seleccionado) {
                activado(text)
            }
        }
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (seleccionado) {
                Icon(
                    painter = painterResource(id = R.drawable.check),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
            }
            Text(
                text = text,
                color = if (seleccionado) Color.White else Color.Black,
                fontSize = 14.sp
            )
        }
    }
}

//Forma de separar dos o varias elecciones
fun separadores(options: List<String>): String {
    return when (options.size) {
        0 -> ""
        1 -> options[0]
        2 -> "${options[0]} & ${options[1]}"
        else -> options.dropLast(1).joinToString(", ") + " & " + options.last()
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewPreferencesScreen() {
    GameRankingPersonalTheme {
        Preferencias()
    }
}