package com.example.gamerankingbag

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamerankingbag.ui.theme.Botones
import com.example.gamerankingbag.ui.theme.GameRankingPersonalTheme
import com.example.gamerankingbag.utils.isLandscape

val CourgetteFontFamily = FontFamily(
    Font(R.font.courgette_regular)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameRankingPersonalTheme {
                GameRankingPrincipal()
            }
        }
    }
}

@Composable
fun GameRankingPrincipal() {

    val context = LocalContext.current

    if (isLandscape()) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 60.dp, start = 24.dp, end = 24.dp, bottom = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo de la app",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Game Ranking",
                    fontSize = 32.sp,
                    fontFamily = CourgetteFontFamily,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(24.dp))

                val buttonModifier = Modifier
                    .width(180.dp)
                    .padding(horizontal = 6.dp, vertical = 6.dp)

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { /* Accion */ },
                        modifier = buttonModifier)
                    { Text("Ranking") }

                    Button(
                        onClick = { context.startActivity(Intent(context, NewPlayerActivity::class.java)) },
                        modifier = buttonModifier)
                    { Text("New Player") }
                    Button(
                        onClick = { /* Accion */ },
                        modifier = buttonModifier)
                    { Text("Preferences") }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(onClick = { /* Accion */ },
                        modifier = buttonModifier)
                    { Text("Licenses") }
                    Button(onClick = { /* Accion */ },
                        modifier = buttonModifier)
                    { Text("About") }
                }
            }
        }
    } else {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Espacio para imagen
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo de la app",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Titulo
                Text(
                    text = "Game Ranking",
                    fontSize = 32.sp,
                    fontFamily = CourgetteFontFamily,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(24.dp))


                // Botones

                Column (
                    modifier = Modifier.padding(12.dp)
                ){
                    Row {
                        MyCard (
                            icono = R.drawable.star,
                            texto = "Ranking"
                        ) {
                            val intent = Intent(context, PlayActivity::class.java)
                            context.startActivity(intent)
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        MyCard (
                            icono = R.drawable.face,
                            texto = "New Player"
                        ) {
                            val intent = Intent(context, NewPlayerActivity::class.java)
                            context.startActivity(intent)
                        }
                    }

                    Row {
                        MyCard (
                            icono = R.drawable.settings,
                            texto = "Preferences"
                        ) {
                            val intent = Intent(context, PreferencesActivity::class.java)
                            context.startActivity(intent)
                        }

                        Spacer(modifier = Modifier.weight(1f))


                        MyCard (
                            icono = R.drawable.users,
                            texto = "Users"
                        ) {
                            val intent = Intent(context, UserActivity::class.java)
                            context.startActivity(intent)
                        }
                    }

                    Row {
                        MyCard (
                            icono = R.drawable.info,
                            texto = "About"
                        ) {
                            //Accion Click
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MyCard  (
    @DrawableRes icono: Int,
    texto: String,
    onClick: () -> Unit
) {
    Card (
        modifier = Modifier
            .width(150.dp)
            .padding(vertical = 6.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = Botones
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = icono),
                contentDescription = "Icono",
                modifier = Modifier.size(30.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = texto)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMainActiviy() {
    GameRankingPersonalTheme {
        GameRankingPrincipal()
    }
}