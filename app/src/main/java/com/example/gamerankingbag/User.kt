package com.example.gamerankingbag

import android.content.Intent
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamerankingbag.ui.theme.Alternativo
import com.example.gamerankingbag.ui.theme.Botones
import com.example.gamerankingbag.ui.theme.GameRankingPersonalTheme
import com.example.gamerankingbag.utils.AppTopBar
import com.example.gamerankingbag.utils.drawerItems
import kotlinx.coroutines.launch

class UserActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameRankingPersonalTheme {
                User()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun User () {

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val context = LocalContext.current

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.googleplay),
                        contentDescription = "Logo drawer",
                        modifier = Modifier.size(80.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                drawerItems.forEach { item ->
                    NavigationDrawerItem(
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label
                            )
                        },
                        label = { Text(item.label) },
                        selected = false,
                        onClick = {
                            scope.launch { drawerState.close() }
                        }
                    )
                }
            }

        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                AppTopBar(
                    titulo = "Users",
                    mostrarMenu = true,
                    mostrarCorazon = true,
                    mostrarLupa = true,
                    mostrarDesplegable = false,
                    onMenuClick = {
                        scope.launch {
                            if (drawerState.isClosed) {
                                drawerState.open()
                            } else {
                                drawerState.close()
                            }
                        }
                    },
                    onTypesClick = {
                        val intent =
                            Intent(context, PlayActivity::class.java)
                        context.startActivity(intent)
                    },
                    onShareClick = { }
                )
            }
        ) { innerPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(30.dp, 30.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Active users:",
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
                        items(listaUsuarios()) { Usuario ->
                            UserCard(
                                icono = Usuario.imagen,
                                nombre = Usuario.nombre,
                                puntos = Usuario.puntos
                            ) {
                                Toast.makeText(
                                    context,
                                    "El usuario ${Usuario.nombre} tiene ${Usuario.puntos} puntos!!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun UserCard (
    @DrawableRes icono: Int,
    nombre: String,
    puntos: Int,
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
            Image(
                painter = painterResource(id = icono),
                contentDescription = nombre,
                modifier = Modifier
                    .weight(1f),
                contentScale = ContentScale.Crop
            )

            Column (
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = nombre,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Puntos: $puntos",
                    fontSize = 13.sp
                    )
            }

        }
    }
}

fun listaUsuarios (): List<Usuario> {
    return listOf(
        Usuario(
            "Beatriz Martos",
            1892,
            R.drawable.image1
        ),
        Usuario(
            "Sandra Alegre",
            3400,
            R.drawable.image2
        ),
        Usuario(
            "Alex Serrat",
            5874,
            R.drawable.image3
        ),
        Usuario(
            "Ana Peris",
            2238,
            R.drawable.image4
        ),
        Usuario(
            "Julian Barros",
            2492,
            R.drawable.image5
        ),
        Usuario(
            "Héctor Moreno",
            4729,
            R.drawable.image6
        ),
        Usuario(
            "Rodrigo Navarro",
            3511,
            R.drawable.image7
        ),
        Usuario(
            "Borja AG",
            8432,
            R.drawable.image8
        ),
    )
}

data class Usuario(
    var nombre: String,
    var puntos: Int,
    @DrawableRes var imagen: Int
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewUser() {
    GameRankingPersonalTheme {
        User()
    }
}