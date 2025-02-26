package com.example.frontend.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun ForgotAccountScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
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

            // Heading
            Text(
                text = "Recover Your Account",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Description
            Text(
                text = "Enter your email address associated with your account, and we will send you a recovery link to regain access.",
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.85f),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp).padding(bottom = 28.dp)
            )

            // Email Input Field
            CustomInputField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = ""
                },
                label = "Enter your email",
                isError = emailError.isNotEmpty(),
                keyboardType = KeyboardType.Email,
                backgroundAlpha = 0.2f,
                textStyle = LocalTextStyle.current.copy(fontSize = 20.sp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (emailError.isNotEmpty()) {
                Text(
                    text = emailError,
                    color = Color.Red,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp, bottom = 10.dp),
                    textAlign = TextAlign.Start
                )
            } else {
                Spacer(modifier = Modifier.height(24.dp))
            }

            // Send Recovery Email Button
            CustomButton(
                text = "Send Recovery Email",
                enabled = email.isNotEmpty(),
                onClick = {
                    if (email.isBlank()) {
                        emailError = "Email is required"
                    } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        emailError = "Enter a valid email address"
                    } else {
                        isSubmitted = true
                    }
                },
                elevation = ButtonDefaults.elevation(if (email.isNotEmpty()) 6.dp else 2.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Cancel Button
            CustomButton(
                text = "Cancel",
                enabled = true,
                onClick = { navController.popBackStack() },
                backgroundColor = Color(0xFFD9534F),
                shape = RoundedCornerShape(16.dp)
            )
        }
    }
}