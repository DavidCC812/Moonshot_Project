package com.example.frontend.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.frontend.R
import com.example.frontend.components.CustomButton

@Composable
fun WelcomeScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Box(
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
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(80.dp))

                // App Logo
                Box(
                    modifier = Modifier
                        .size(160.dp)
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                }

                Spacer(modifier = Modifier.height(120.dp))

                // Login Buttons
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val buttonColor = Color(0xFFBCCCDC)
                    val textColor = Color.Black
                    val buttonRadius = 30.dp
                    val buttonHeight = 50.dp

                    CustomButton(
                        text = "Login with Google",
                        backgroundColor = buttonColor,
                        textColor = textColor,
                        icon = {
                            Image(
                                painter = painterResource(id = R.drawable.ic_google),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        onClick = { /* Handle Google Login */ },
                        alignIconLeft = true,
                        cornerRadius = buttonRadius,
                        buttonHeight = buttonHeight
                    )

                    CustomButton(
                        text = "Login with Facebook",
                        backgroundColor = buttonColor,
                        textColor = textColor,
                        icon = {
                            Image(
                                painter = painterResource(id = R.drawable.ic_facebook),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        onClick = { /* Handle Facebook Login */ },
                        alignIconLeft = true,
                        cornerRadius = buttonRadius,
                        buttonHeight = buttonHeight
                    )

                    CustomButton(
                        text = "Login with Phone",
                        backgroundColor = buttonColor,
                        textColor = textColor,
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Phone,
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        onClick = { navController.navigate("phone") },
                        alignIconLeft = true,
                        cornerRadius = buttonRadius,
                        buttonHeight = buttonHeight
                    )

                    CustomButton(
                        text = "Login with Email",
                        backgroundColor = buttonColor,
                        textColor = textColor,
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Email,
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        onClick = { navController.navigate("email") },
                        alignIconLeft = true,
                        cornerRadius = buttonRadius,
                        buttonHeight = buttonHeight
                    )
                }

                Spacer(modifier = Modifier.height(50.dp))

                Text(
                    text = "Having trouble logging in?",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(bottom = 12.dp)
                        .clickable { navController.navigate("forgot_email") }
                )
            }
        }
    }
}