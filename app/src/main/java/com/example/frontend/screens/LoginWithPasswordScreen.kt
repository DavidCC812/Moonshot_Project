package com.example.frontend.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.frontend.components.CustomButton
import com.example.frontend.components.CustomInputField

@Composable
fun LoginWithPasswordScreen(navController: NavHostController, identifier: String) {
    var password by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF3A6EA5), Color(0xFF5A92D5))
                )
            )
            .padding(horizontal = 24.dp),
        color = Color.Transparent
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "App Logo",
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 40.dp)
            )

            // Identifier Field (Email/Phone)
            CustomInputField(
                value = identifier,
                onValueChange = {},
                label = "",
                isError = false,
                keyboardType = KeyboardType.Text,
                enabled = false,
                backgroundAlpha = 0.25f,
                textStyle = LocalTextStyle.current.copy(fontSize = 20.sp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // ✅ Password Input Field with Eye Toggle
            CustomInputField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = ""
                },
                label = "Enter your password",
                isError = passwordError.isNotEmpty(),
                keyboardType = KeyboardType.Password,
                backgroundAlpha = 0.2f,
                textStyle = LocalTextStyle.current.copy(fontSize = 20.sp)
            )

            if (passwordError.isNotEmpty()) {
                Text(
                    text = passwordError,
                    color = Color.Red,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp, bottom = 10.dp),
                    textAlign = TextAlign.Start
                )
            } else {
                Spacer(modifier = Modifier.height(12.dp))
            }

            TextButton(
                onClick = { navController.navigate("forgot_password") },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Forgot your password?",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Continue Button
            CustomButton(
                text = "Continue",
                enabled = password.isNotEmpty(),
                onClick = {
                    if (password.isBlank()) {
                        passwordError = "Password is required"
                    } else {
                        navController.navigate("home")
                    }
                },
                disabledBackgroundAlpha = 0.5f
            )
        }
    }
}