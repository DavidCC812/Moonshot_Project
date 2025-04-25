package com.example.frontend.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.frontend.components.CustomButton
import com.example.frontend.components.CustomInputField

@Composable
fun EmailScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }

    val registeredEmails = listOf(
        "davidcc812@gmail.com",
        "example@example.com",
        "frontend@mock.com"
    )

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
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // App Logo
            Box(
                modifier = Modifier
                    .size(160.dp)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {}

            Spacer(modifier = Modifier.height(210.dp))

            // Input Field
            CustomInputField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = ""
                },
                label = "Enter your email",
                isError = emailError.isNotEmpty(),
                keyboardType = KeyboardType.Email,
                textStyle = LocalTextStyle.current.copy(fontSize = 20.sp, color = Color.Black)
            )

            if (emailError.isNotEmpty()) {
                Text(
                    text = emailError,
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp, bottom = 10.dp),
                    textAlign = TextAlign.Start
                )
            } else {
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Continue Button
            CustomButton(
                text = "Continue",
                enabled = email.isNotEmpty(),
                onClick = {
                    if (email.isBlank()) {
                        emailError = "Email is required"
                    } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        emailError = "Enter a valid email address"
                    } else {
                        if (registeredEmails.contains(email)) {
                            navController.navigate("login_with_password/$email")
                        } else {
                            navController.navigate("otp_verification/email/$email")
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}