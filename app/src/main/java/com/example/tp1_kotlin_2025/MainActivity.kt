package com.example.tp1_kotlin_2025
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tp1_kotlin_2025.ui.theme.TP1Kotlin2025Theme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tp1_kotlin_2025.ui.screens.LoginScreen
import com.example.tp1_kotlin_2025.ui.screens.RegisterScreen
import com.example.tp1_kotlin_2025.ui.screens.WelcomeScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(navController, startDestination = "login") {
                composable("login") {
                    LoginScreen(
                        onLoginSuccess = { navController.navigate("welcome") },
                        onNavigateToRegister = { navController.navigate("register") }
                    )
                }
                composable("register") {
                    RegisterScreen(
                        onRegistrationSuccess = { navController.popBackStack() }, // vuelve al login
                        onBackToLogin = { navController.popBackStack() }
                    )
                }
                composable("welcome") {
                    WelcomeScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TP1Kotlin2025Theme {
        Greeting("Android")
    }
}