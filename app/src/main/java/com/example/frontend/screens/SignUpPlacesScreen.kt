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
import com.example.frontend.components.BackButton
import com.example.frontend.components.CustomButton
import com.example.frontend.components.CustomChipGroup
import com.example.frontend.components.SignUpProgressBar

@Composable
fun SignUpPlacesScreen(navController: NavHostController) {
    val options = listOf(
        "Museums",
        "Historical Sites",
        "Parks",
        "Restaurants",
        "Zoos",
        "Theaters",
        "Shopping Centers",
    )

    var selectedOptions by remember { mutableStateOf(setOf<String>()) }

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
            // Progress Bar
            Spacer(modifier = Modifier.height(20.dp))
            SignUpProgressBar(currentStep = 6, totalSteps = 6)
            BackButton(navController)

            Spacer(modifier = Modifier.weight(0.5f))
            Text(
                text = "Select up to three places you wish to visit",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Spacer(modifier = Modifier.weight(0.4f))

            // Chip Selection
            CustomChipGroup(
                options = options,
                selectedOptions = selectedOptions,
                onSelectionChanged = { newSelection ->
                    if (newSelection.size <= 3) {
                        selectedOptions = newSelection
                    }
                }
            )

            Spacer(modifier = Modifier.height(40.dp))

            CustomButton(
                text = "Next",
                enabled = selectedOptions.isNotEmpty(),
                onClick = { navController.navigate("home") },
                fontWeight = if (selectedOptions.isNotEmpty()) FontWeight.Bold else FontWeight.Medium
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}