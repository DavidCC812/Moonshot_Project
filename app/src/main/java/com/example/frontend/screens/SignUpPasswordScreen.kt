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
fun SignUpPasswordScreen(navController: NavHostController) {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isSubmitted by remember { mutableStateOf(false) }

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
            // App Logo
            Text(
                text = "App Logo",
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 40.dp)
            )

            // Title
            Text(
                text = "Create Your Password",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // Description
            Text(
                text = "Choose a strong password. It must be at least 6 characters long and contain at least one number.",
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.85f),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp).padding(bottom = 28.dp)
            )

            // Password Input Field
            CustomInputField(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                isError = isSubmitted && (password.length < 6 || !password.any { it.isDigit() }),
                keyboardType = KeyboardType.Password,
                backgroundAlpha = 0.25f,
                textStyle = LocalTextStyle.current.copy(fontSize = 20.sp)
            )

            if (isSubmitted && (password.length < 6 || !password.any { it.isDigit() })) {
                Text(
                    text = "Password must be at least 6 characters and contain a number",
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

            CustomInputField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = "Confirm Password",
                isError = isSubmitted && confirmPassword != password,
                keyboardType = KeyboardType.Password,
                backgroundAlpha = 0.25f,
                textStyle = LocalTextStyle.current.copy(fontSize = 20.sp)
            )

            if (isSubmitted && confirmPassword != password) {
                Text(
                    text = "Passwords do not match",
                    color = Color.Red,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp, bottom = 10.dp),
                    textAlign = TextAlign.Start
                )
            }

            Spacer(modifier = Modifier.height(38.dp))

            // Next Button
            CustomButton(
                text = "Next",
                enabled = password.length >= 6 && password.any { it.isDigit() } && password == confirmPassword,
                onClick = {
                    isSubmitted = true
                    if (password.length >= 6 && password.any { it.isDigit() } && password == confirmPassword) {
                        navController.navigate("signup_accessibility")
                    }
                },
                fontWeight = if (password.length >= 6 && password.any { it.isDigit() } && password == confirmPassword) FontWeight.Bold else FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Back Button
            CustomButton(
                text = "Back",
                enabled = true,
                onClick = { navController.popBackStack() },
                backgroundColor = Color(0xFF999999)
            )
        }
    }
}