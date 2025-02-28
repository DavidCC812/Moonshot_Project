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
fun SignUpPhoneScreen(navController: NavHostController) {
    var phoneNumber by remember { mutableStateOf("") }
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
                text = "Verify Your Phone Number",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // Description
            Text(
                text = "Enter your phone number. This will be used for account verification and security purposes.",
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.85f),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp).padding(bottom = 28.dp)
            )

// Phone Number Input with +33 Prefix
            CustomInputField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = "Phone Number",
                isError = isSubmitted && phoneNumber.length != 10,
                keyboardType = KeyboardType.Phone,
                backgroundAlpha = 0.25f,
                textStyle = LocalTextStyle.current.copy(fontSize = 20.sp),
                leadingIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(start = 12.dp, top = 1.dp)
                    ) {
                        Text(
                            text = "+33",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            )

            if (isSubmitted && phoneNumber.length != 10) {
                Text(
                    text = "Enter a valid 10-digit phone number",
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

            // Next Button
            CustomButton(
                text = "Next",
                enabled = phoneNumber.length == 10,
                onClick = {
                    isSubmitted = true
                    if (phoneNumber.length == 10) {
                        navController.navigate("signup_password")
                    }
                }
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