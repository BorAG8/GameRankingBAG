package com.example.gamerankingbag.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class DrawerItem(val label: String, val icon: ImageVector)

val drawerItems = listOf(
    DrawerItem("Home", Icons.Filled.Home),
    DrawerItem("Profiles", Icons.Filled.Person),
    DrawerItem("Settings", Icons.Filled.Settings)
)