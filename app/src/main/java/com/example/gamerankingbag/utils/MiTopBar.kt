package com.example.gamerankingbag.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MenuDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    titulo: String,
    mostrarMenu: Boolean = true,
    mostrarCorazon: Boolean = false,
    mostrarLupa: Boolean = false,
    mostrarDesplegable: Boolean = false,
    onMenuClick: (() -> Unit)? = null,
    onLupaClick: (() -> Unit)? = null,
    onTypesClick: () -> Unit = {},
    onShareClick: () -> Unit = {}
) {
    var corazon by remember { mutableStateOf(false) }
    var overflowExpanded by remember { mutableStateOf(false) }

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = titulo,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp // Tamaño del titulo
            )
        },
        navigationIcon = {
            if (mostrarMenu && onMenuClick != null) {
                IconButton(onClick = onMenuClick) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menu"
                    )
                }
            }
        },
        actions = {

            if (mostrarCorazon) {
                IconButton(
                    onClick = { corazon = !corazon }
                ) {
                    Icon(
                        imageVector = if (corazon) {
                            Icons.Filled.Favorite
                        } else {
                            Icons.Outlined.FavoriteBorder
                        },
                        contentDescription = "Favorito"
                    )
                }
            }

            if (mostrarLupa) {
                IconButton(
                    onClick = { onLupaClick?.invoke() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Buscar"
                    )
                }
            }

            if (mostrarDesplegable) {
                Box {
                    IconButton(
                        onClick = { overflowExpanded = true }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "Más opciones"
                        )
                    }

                    DropdownMenu(
                        expanded = overflowExpanded,
                        onDismissRequest = { overflowExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Types") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Image,
                                    contentDescription = null
                                )
                            },
                            colors = MenuDefaults.itemColors(
                                textColor = Color.Black,
                                leadingIconColor = Color.Black
                            ),
                            onClick = {
                                overflowExpanded = false
                                onTypesClick()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Share") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Share,
                                    contentDescription = null
                                )
                            },
                            colors = MenuDefaults.itemColors(
                                textColor = Color.Black,
                                leadingIconColor = Color.Black
                            ),
                            onClick = {
                                overflowExpanded = false
                                onShareClick()
                            }
                        )
                    }
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}
