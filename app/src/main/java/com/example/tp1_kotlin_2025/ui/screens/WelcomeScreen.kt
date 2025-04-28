// WelcomeScreen.kt
package com.example.tp1_kotlin_2025.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tp1_kotlin_2025.R
import com.example.tp1_kotlin_2025.ui.theme.LightGrayBackground

@Composable
fun WelcomeScreen() {
    // Hardcoded username
    val userName = "Juan Torres"
    var platform by remember { mutableStateOf("") }
    var preferences by remember { mutableStateOf(setOf<String>()) }
    var otherPreference by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGrayBackground)
            .padding(16.dp)
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(bottom = 16.dp)
                )

                Text(
                    text = "¡Bienvenido, $userName!",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0D47A1),
                    modifier = Modifier.padding(bottom = 8.dp),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Seleccioná tu plataforma:",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    RadioButton(
                        selected = platform == "Android",
                        onClick = { platform = "Android" },
                        colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF0D47A1))
                    )
                    Text("Android", modifier = Modifier.padding(end = 16.dp))

                    RadioButton(
                        selected = platform == "iOS",
                        onClick = { platform = "iOS" },
                        colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF0D47A1))
                    )
                    Text("iOS")
                }

                if (platform.isNotEmpty()) {
                    val logo = if (platform == "Android") R.drawable.android_logo else R.drawable.ios_logo
                    Image(
                        painter = painterResource(id = logo),
                        contentDescription = null,
                        modifier = Modifier
                            .size(80.dp)
                            .padding(vertical = 16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Seleccioná tus preferencias:",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                val opciones = listOf("Programación", "Redes", "Seguridad", "Hardware", "Otra")
                opciones.forEach { opcion ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Checkbox(
                            checked = preferences.contains(opcion),
                            onCheckedChange = {
                                preferences = if (preferences.contains(opcion)) preferences - opcion else preferences + opcion
                            },
                            colors = CheckboxDefaults.colors(checkedColor = Color(0xFF0D47A1))
                        )
                        Text(opcion)
                    }
                    if (opcion == "Otra" && preferences.contains("Otra")) {
                        OutlinedTextField(
                            value = otherPreference,
                            onValueChange = { otherPreference = it },
                            label = { Text("Otra preferencia") },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp)),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Color(0xFFF2F7FB),
                                unfocusedContainerColor = Color(0xFFF2F7FB),
                                disabledContainerColor = Color(0xFFF2F7FB),
                                focusedBorderColor = Color(0xFF42A5F5),
                                unfocusedBorderColor = Color.Gray,
                                disabledBorderColor = Color.Gray
                            )
                        )
                    }
                }
            }
        }
    }
}
