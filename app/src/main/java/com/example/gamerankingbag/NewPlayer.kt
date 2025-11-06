package com.example.gamerankingbag

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamerankingbag.ui.theme.GameRankingPersonalTheme
import com.example.gamerankingbag.utils.isLandscape
import kotlinx.coroutines.selects.select


class NewPlayerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameRankingPersonalTheme {
                MenuNewPlayer()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuNewPlayer() {

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var nickName by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    var firstNameError by remember { mutableStateOf(false) }
    var lastNameError by remember { mutableStateOf(false) }
    var nickNameError by remember { mutableStateOf(false) }
    var telefonoError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }

    val playerData = mapOf(
        "Goku" to PlayerInfo("Goku", "Kakaroto", "662547986", "goku@dbz.com"),
        "Naruto" to PlayerInfo("Naruto", "Uzumaki", "671569856", "naruto@ninja.com"),
        "IronMan" to PlayerInfo("Tony", "Stark", "692478236", "ironman@starkindustries.com"),
        "Mononoke" to PlayerInfo("Princesa", "Mononoke", "648521452", "mononoke@kodama.es")
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(top = 40.dp, start = if (isLandscape()) 200.dp else 24.dp, end = 24.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // First Name
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.usuario),
                    contentDescription = "Person Icon",
                    modifier = Modifier.size(60.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                )
                Spacer(modifier = Modifier.width(16.dp))
                OutlinedTextField(
                    value = firstName,
                    onValueChange = {
                        firstName = it
                        if (firstName.isNotBlank()) firstNameError = false
                    },
                    label = {
                        Text(
                            text = if (firstNameError) "*Obligatorio" else "First Name",
                            color = if (firstNameError) Color.Red else MaterialTheme.colorScheme.primary
                        )
                    },
                    isError = firstNameError,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = if (firstNameError) Color.Red else MaterialTheme.colorScheme.tertiary,
                        unfocusedContainerColor = if (firstNameError) Color(0xFFEF808C) else Color.Cyan,
                        focusedContainerColor = if (firstNameError) Color(0xFFEF808C) else MaterialTheme.colorScheme.primary,
                        focusedIndicatorColor = if (firstNameError) Color.Red else MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = if (firstNameError) Color.Red else MaterialTheme.colorScheme.outline
                    )
                )


            }

            // Last Name
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 76.dp, top = 8.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = lastName,
                    onValueChange = {
                        lastName = it
                        if (lastName.isNotBlank()) lastNameError = false
                    },
                    label = {
                        Text(
                            text = if (lastNameError) "*Obligatorio" else "Last Name",
                            color = if (lastNameError) Color.Red else MaterialTheme.colorScheme.primary
                        )
                    },
                    isError = lastNameError,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = if (lastNameError) Color.Red else MaterialTheme.colorScheme.tertiary,
                        unfocusedContainerColor = if (lastNameError) Color(0xFFEF808C) else Color.Cyan, //No entiendo porque no me aplica estos colores al fondo pero por ejemplo el cyan si
                        focusedContainerColor = if (lastNameError) Color(0xFFEF808C) else MaterialTheme.colorScheme.primary,
                        focusedIndicatorColor = if (lastNameError) Color.Red else MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = if (lastNameError) Color.Red else MaterialTheme.colorScheme.outline
                    )
                )
            }

            // NickName
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.nickname),
                    contentDescription = "NickName Icon",
                    modifier = Modifier.size(60.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                )
                Spacer(modifier = Modifier.width(16.dp))
                
                MyExposedDropDownMenu(
                    nickName = nickName,
                    nickNameError = nickNameError,
                    onNickChange = { selectedNick ->
                        nickName = selectedNick
                        nickNameError = false
                        playerData[selectedNick]?.let { data ->
                            firstName = data.firstName
                            lastName = data.lastName
                            telefono = data.telefono
                            email = data.email
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.android),
                    contentDescription = "Android Logo",
                    modifier = Modifier.size(80.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.width(40.dp))

                Button(
                    onClick = { /* Accion */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    shape = RoundedCornerShape(25.dp)
                ) {
                    Text("Preferences", fontSize = 16.sp)
                }
            }


            Spacer(modifier = Modifier.height(24.dp))

            // Telefono
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.phone),
                    contentDescription = "Phone Icon",
                    modifier = Modifier.size(60.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                )
                Spacer(modifier = Modifier.width(16.dp))
                OutlinedTextField(
                    value = telefono,
                    onValueChange = {
                        telefono = it
                        if (telefono.isNotBlank()) telefonoError = false
                    },
                    label = {
                        Text(
                            text = if (telefonoError) "*Obligatorio" else "Phone",
                            color = if (telefonoError) Color.Red else MaterialTheme.colorScheme.primary
                        )
                    },
                    isError = telefonoError,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = if (telefonoError) Color.Red else MaterialTheme.colorScheme.tertiary,
                        unfocusedContainerColor = if (telefonoError) Color(0xFFEF808C) else Color.Cyan,
                        focusedContainerColor = if (telefonoError) Color(0xFFEF808C) else MaterialTheme.colorScheme.primary,
                        focusedIndicatorColor = if (telefonoError) Color.Red else MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = if (telefonoError) Color.Red else MaterialTheme.colorScheme.outline
                    )
                )
            }

            // Email
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.email),
                    contentDescription = "Email Icon",
                    modifier = Modifier.size(60.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                )
                Spacer(modifier = Modifier.width(16.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        if (email.isNotBlank()) emailError = false
                    },
                    label = {
                        Text(
                            text = if (emailError) "*Obligatorio" else "E-mail",
                            color = if (emailError) Color.Red else MaterialTheme.colorScheme.primary
                        )
                    },
                    isError = emailError,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = if (emailError) Color.Red else MaterialTheme.colorScheme.tertiary,
                        unfocusedContainerColor = if (emailError) Color(0xFFEF808C) else Color.Cyan,
                        focusedContainerColor = if (emailError) Color(0xFFEF808C) else MaterialTheme.colorScheme.primary,
                        focusedIndicatorColor = if (emailError) Color.Red else MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = if (emailError) Color.Red else MaterialTheme.colorScheme.outline
                    )
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    firstNameError = firstName.isBlank()
                    lastNameError = lastName.isBlank()
                    nickNameError = nickName.isBlank()
                    telefonoError = telefono.isBlank()
                    emailError = email.isBlank()
                },
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(25),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.usuario),
                        contentDescription = "Add Icon",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Add new player", fontSize = 16.sp)
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyExposedDropDownMenu(
    nickName: String,
    nickNameError: Boolean,
    onNickChange: (String) -> Unit
) {
    val options = listOf("Goku", "Naruto", "IronMan", "Mononoke")
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = nickName,
            onValueChange = {},
            readOnly = true,
            shape = RoundedCornerShape(8.dp),
            label = {
                Text(
                    text = if (nickNameError) "*Obligatorio" else "NickName",
                    color = if (nickNameError) Color.Red else MaterialTheme.colorScheme.primary
                )
            },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = TextFieldDefaults.colors(
                focusedTextColor = if (nickNameError) Color.Red else MaterialTheme.colorScheme.tertiary,
                unfocusedContainerColor = if (nickNameError) Color(0xFFEF808C) else Color.Cyan,
                focusedContainerColor = if (nickNameError) Color(0xFFEF808C) else MaterialTheme.colorScheme.primary,
                focusedIndicatorColor = if (nickNameError) Color.Red else MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = if (nickNameError) Color.Red else MaterialTheme.colorScheme.outline
            ),
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onNickChange(option)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}

data class PlayerInfo(
    val firstName: String,
    val lastName: String,
    val telefono: String,
    val email: String
)


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewNewPlayer() {
    GameRankingPersonalTheme {
        MenuNewPlayer()
    }
}