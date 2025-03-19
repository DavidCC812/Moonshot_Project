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
fun PhoneScreen(navController: NavHostController) {
    var phone by remember { mutableStateOf("") }
    var phoneError by remember { mutableStateOf("") }

    val registeredPhones = listOf(
        "0630300181",
        "0123456789",
        "0987654321"
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
            // **App Logo Placeholder
            Box(
                modifier = Modifier
                    .size(160.dp)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
            }

            Spacer(modifier = Modifier.height(210.dp))

            CustomInputField(
                value = phone,
                onValueChange = {
                    phone = it
                    phoneError = ""
                },
                label = "Enter your phone number",
                isError = phoneError.isNotEmpty(),
                keyboardType = KeyboardType.Phone,
                textStyle = LocalTextStyle.current.copy(fontSize = 20.sp, color = Color.Black)
            )

            if (phoneError.isNotEmpty()) {
                Text(
                    text = phoneError,
                    color = Color.Red,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp, bottom = 10.dp),
                    textAlign = TextAlign.Start
                )
            } else {
                Spacer(modifier = Modifier.height(16.dp))
            }

            CustomButton(
                text = "Continue",
                enabled = phone.isNotEmpty(),
                onClick = {
                    if (phone.isBlank()) {
                        phoneError = "Phone number is required"
                    } else if (!phone.matches(Regex("^\\d{10,15}\$"))) {
                        phoneError = "Enter a valid phone number"
                    } else {
                        if (registeredPhones.contains(phone)) {
                            navController.navigate("login_with_password/$phone")
                        } else {
                            navController.navigate("signup_name")
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.height(50.dp))

        }
    }
}