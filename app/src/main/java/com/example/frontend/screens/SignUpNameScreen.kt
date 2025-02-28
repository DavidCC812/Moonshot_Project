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
fun SignUpNameScreen(navController: NavHostController) {
    var fullName by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var fullNameError by remember { mutableStateOf(false) }

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
                text = "Create Your Profile",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // Description
            Text(
                text = "Enter your full name and choose a nickname. Your nickname will be used in the forum to keep your identity private.",
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.85f),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp).padding(bottom = 28.dp)
            )

            // Full Name Input Field
            CustomInputField(
                value = fullName,
                onValueChange = {
                    fullName = it
                    fullNameError = it.isBlank()
                },
                label = "Full Name",
                isError = fullNameError,
                keyboardType = KeyboardType.Text,
                backgroundAlpha = 0.25f,
                textStyle = LocalTextStyle.current.copy(fontSize = 20.sp)
            )

            if (fullNameError) {
                Text(
                    text = "Full Name is required",
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

            // Nickname Input Field
            CustomInputField(
                value = nickname,
                onValueChange = { nickname = it },
                label = "Nickname (Optional)",
                isError = false,
                keyboardType = KeyboardType.Text,
                backgroundAlpha = 0.25f,
                textStyle = LocalTextStyle.current.copy(fontSize = 20.sp)
            )

            Spacer(modifier = Modifier.height(38.dp))

            // Next Button
            CustomButton(
                text = "Next",
                enabled = fullName.isNotBlank(),
                onClick = { navController.navigate("signup_phone") },
                fontWeight = if (fullName.isNotBlank()) FontWeight.Bold else FontWeight.Medium
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