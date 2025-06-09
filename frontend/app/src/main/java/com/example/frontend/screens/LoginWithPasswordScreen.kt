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
                    colors = listOf(
                        Color(0xFFF8FAFC),
                        Color(0xFFD9EAFD),
                        Color(0xFFBCCCDC)
                    )
                )
            )
            .padding(horizontal = 24.dp),
        color = Color.Transparent
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))

            //App Logo
            Box(
                modifier = Modifier
                    .size(160.dp)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
            }

            Spacer(modifier = Modifier.height(140.dp))

            // Identifier Field
            CustomInputField(
                value = identifier,
                onValueChange = {},
                label = "",
                isError = false,
                keyboardType = KeyboardType.Text,
                enabled = false,
                backgroundAlpha = 0.25f,
                textStyle = LocalTextStyle.current.copy(fontSize = 20.sp, color = Color.Black)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password Input Field
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
                textStyle = LocalTextStyle.current.copy(fontSize = 20.sp, color = Color.Black)
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
                Spacer(modifier = Modifier.height(5.dp))
            }

            // Forgot Password
            TextButton(
                onClick = { navController.navigate("forgot_password") },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Forgot your password?",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Continue Button
            CustomButton(
                text = "Continue",
                enabled = password.isNotEmpty(),
                backgroundColor = if (password.isNotEmpty()) Color(0xFFB0BBC6) else Color(0xFFBCCCDC),
                textColor = Color.Black,
                onClick = {
                    if (password.isBlank()) {
                        passwordError = "Password is required"
                    } else {
                        navController.navigate("home")
                    }
                }
            )
        }
    }
}