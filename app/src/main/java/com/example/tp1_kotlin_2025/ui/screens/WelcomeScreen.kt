package com.example.tp1_kotlin_2025.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tp1_kotlin_2025.R

@Composable
fun WelcomeScreen(userName: String = "Juan Torres") {
    var platform by remember { mutableStateOf("") }
    var preferences by remember { mutableStateOf(setOf<String>()) }
    var otherPreference by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Bienvenido a la aplicación $userName",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Plataforma
        Text("Seleccioná tu plataforma:", style = MaterialTheme.typography.titleMedium)

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = platform == "Android",
                onClick = { platform = "Android" }
            )
            Text("Android")

            Spacer(modifier = Modifier.width(16.dp))

            RadioButton(
                selected = platform == "iOS",
                onClick = { platform = "iOS" }
            )
            Text("iOS")
        }

        if (platform.isNotEmpty()) {
            val logo = if (platform == "Android")   R.drawable.android_logo else R.drawable.ios_logo
            Image(
                painter = painterResource(id = logo),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(top = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Preferencias
        Text("Seleccioná tus preferencias:", style = MaterialTheme.typography.titleMedium)

        val opciones = listOf("Programación", "Redes", "Seguridad", "Hardware", "Otra")
        opciones.forEach { opcion ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = preferences.contains(opcion),
                    onCheckedChange = {
                        preferences = if (preferences.contains(opcion)) {
                            preferences - opcion
                        } else {
                            preferences + opcion
                        }
                    }
                )
                Text(opcion)
            }

            if (opcion == "Otra" && preferences.contains("Otra")) {
                OutlinedTextField(
                    value = otherPreference,
                    onValueChange = { otherPreference = it },
                    label = { Text("Otra preferencia") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
