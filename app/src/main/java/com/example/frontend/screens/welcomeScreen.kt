package com.example.frontend.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.frontend.R

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
                        colors = listOf(Color(0xFF3A6EA5), Color(0xFF5A92D5))
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
                Spacer(modifier = Modifier.height(110.dp))

                // App Logo Placeholder
                Text(
                    text = "App Logo",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.shadow(2.dp)
                )

                Spacer(modifier = Modifier.height(90.dp))

                // Login Buttons
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(22.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CustomButton(
                        text = "Login with Google",
                        backgroundColor = Color.White,
                        textColor = Color.Black,
                        iconRes = painterResource(id = R.drawable.ic_google),
                        outline = Color.Gray.copy(alpha = 0.3f),
                        onClick = { /* Handle Google Login */ }
                    )

                    CustomButton(
                        text = "Login with Facebook",
                        backgroundColor = Color(0xFF1558A8),
                        textColor = Color.White,
                        iconRes = painterResource(id = R.drawable.ic_facebook),
                        onClick = { /* Handle Facebook Login */ }
                    )

                    CustomButton(
                        text = "Login with Phone Number",
                        backgroundColor = Color(0xFF4A90E2),
                        textColor = Color.White,
                        icon = Icons.Filled.Phone,
                        onClick = { navController.navigate("phone")}
                    )

                    CustomButton(
                        text = "Login with Email",
                        backgroundColor = Color(0xFF3A6EA5),
                        textColor = Color.White,
                        icon = Icons.Filled.Email,
                        onClick = { navController.navigate("email") }
                    )
                }

                Spacer(modifier = Modifier.height(35.dp))

                Text(
                    text = "Having trouble logging in?",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .clickable { navController.navigate("forgot_email") }
                        .shadow(1.dp)
                )
            }
        }
    }
}

// **Reusable Button Component**
@Composable
fun CustomButton(
    text: String,
    backgroundColor: Color,
    textColor: Color,
    iconRes: Painter? = null,
    icon: ImageVector? = null,
    outline: Color? = null,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        shape = RoundedCornerShape(30.dp),
        elevation = ButtonDefaults.elevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .let { if (outline != null) it.border(1.dp, outline, RoundedCornerShape(30.dp)) else it }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            iconRes?.let {
                Image(
                    painter = it,
                    contentDescription = null,
                    modifier = Modifier.size(28.dp).padding(end = 8.dp)
                )
            }

            icon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    tint = textColor,
                    modifier = Modifier.size(28.dp).padding(end = 8.dp)
                )
            }

            Text(
                text = text,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
        }
    }
}