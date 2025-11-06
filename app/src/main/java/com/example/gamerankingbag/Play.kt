package com.example.gamerankingbag

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamerankingbag.ui.theme.Alternativo
import com.example.gamerankingbag.ui.theme.Botones
import com.example.gamerankingbag.ui.theme.GameRankingPersonalTheme

class PlayActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameRankingPersonalTheme {
                Play()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Play () {

    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp, 50.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            // Titulo plataformas
            Text(
                text = "Platformas:",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = CourgetteFontFamily,
                color = Alternativo
            )
            Spacer(modifier = Modifier.height(8.dp))

            ChipGroup(
                items = listOf("PS5", "Xbox X/S", "Switch", "PC", "Steam", "GeForce Now")
            ) { platform ->
                Toast.makeText(
                    context,
                    "Has seleccionado $platform",
                    Toast.LENGTH_SHORT
                ).show()
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Titulo juegos por consola
            Text(
                text = "Géneros:",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = CourgetteFontFamily,
                color = Alternativo
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(listaJuegos()) { juego ->
                    GameCard(
                        icono = juego.imagen,
                        texto = juego.genero
                    ) {
                        Toast.makeText(
                            context,
                            "El juego ${juego.nombre} del género tipo ${juego.genero}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipGroup(
    items: List<String>,
    onSelected: (String) -> Unit
) {
    var selectedItem by remember { mutableStateOf<String?>(null) }

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(items.size) { index ->
            val label = items[index]
            val isSelected = selectedItem == label

            FilterChip(
                selected = isSelected,
                onClick = {
                    selectedItem = label
                    onSelected(label)
                },
                leadingIcon = if (isSelected){
                    {
                        Icon(
                            painter = painterResource(id = R.drawable.check),
                            contentDescription = "Seleccionado",
                            modifier = Modifier.size(18.dp)
                        )
                    }

                }else null,
                label = { Text(label) },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Botones,
                    selectedContainerColor = Alternativo,
                    labelColor = MaterialTheme.colorScheme.onPrimary,
                    selectedLabelColor = MaterialTheme.colorScheme.onSecondary
                )
            )
        }
    }
}

@Composable
fun GameCard (
    @DrawableRes icono: Int,
    texto: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Botones)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = texto,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )

            Image(
                painter = painterResource(id = icono),
                contentDescription = texto,
                modifier = Modifier
                    .weight(1f),
                contentScale = ContentScale.Crop
            )
        }
    }
}

fun listaJuegos (): List<Juego> {
    return listOf(
        Juego(
            "Rpg",
            "Hogwrats",
            R.drawable.hogwarts
        ),
        Juego(
            "Aventura",
            "OuterWilds",
            R.drawable.logo
        ),
        Juego(
            "Coches",
            "Forza Horizon",
            R.drawable.forza
        ),
        Juego(
            "Luchas",
            "Street Fighter II",
            R.drawable.street
        ),
        Juego(
            "Shooter",
            "Arc Riders",
            R.drawable.arc
        )
    )
}

data class Juego(
    var genero: String,
    var nombre: String,
    @DrawableRes var imagen: Int
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPlay() {
    GameRankingPersonalTheme {
        Play()
    }
}