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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.frontend.components.CustomButton

@Composable
fun SignUpAccessibilityScreen(navController: NavHostController) {
    val options = listOf(
        "No disabilities",
        "Wheelchair Access",
        "Audio Navigation",
        "Visual Navigation"
    )

    var selectedOptions by remember { mutableStateOf(setOf<String>()) }

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
                text = "Select Accessibility Needs",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Text(
                text = "Please let us know if you require specific accessibility features.",
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.85f),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 28.dp)
            )

            // Accessibility Options
            options.forEach { option ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = selectedOptions.contains(option),
                        onCheckedChange = {
                            selectedOptions = if (it) {
                                selectedOptions + option
                            } else {
                                selectedOptions - option
                            }
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFF3A6EA5),
                            uncheckedColor = Color.White.copy(alpha = 0.8f)
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = option,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))


            CustomButton(
                text = "Finish",
                enabled = selectedOptions.isNotEmpty(),
                onClick = { navController.navigate("home") },
                backgroundColor = if (selectedOptions.isNotEmpty()) Color(0xFF3A6EA5) else Color.Gray,
                textColor = Color.White
            )

            Spacer(modifier = Modifier.height(32.dp))


            CustomButton(
                text = "Back",
                enabled = true,
                onClick = { navController.popBackStack() },
                backgroundColor = Color(0xFF999999),
                textColor = Color.White
            )
        }
    }
}