package com.example.frontend.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.frontend.components.BackButton
import com.example.frontend.components.MapScreen
import com.google.android.gms.maps.model.LatLng

@Composable
fun ItineraryStepsScreen(navController: NavHostController) {
    val stepLocations = listOf(
        LatLng(48.8606, 2.3376), // Louvre Museum
        LatLng(48.852968, 2.349902), // Notre Dame
        LatLng(48.8584, 2.2945), // Eiffel Tower
        LatLng(48.8738, 2.2950), // Arc de Triomphe
        LatLng(48.8462, 2.3371) // Pantheon
    )

    var selectedStep by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = { HomeTopBar(navController) },
        bottomBar = { BottomNavBar(navController, selectedScreen = "search") },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFF8FAFC),
                            Color(0xFFD9EAFD)
                        )
                    )
                )
                .padding(padding)
        ) {
            BackButton(navController)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                MapScreen(stepLocations, selectedStep)
            }

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                itemsIndexed(stepLocations) { index, _ ->
                    StepItem(index, selectedStep) {
                        selectedStep = index
                    }
                }
            }
        }
    }
}

@Composable
fun StepItem(index: Int, selectedStep: Int, onStepClick: () -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable {
                onStepClick()
                isExpanded = !isExpanded
            },
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        backgroundColor = if (index == selectedStep) Color(0xFF9AA6B2) else Color.White
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "Step ${index + 1}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = when (index) {
                    0 -> "Visit the Louvre Museum"
                    1 -> "Explore Notre Dame Cathedral"
                    2 -> "Admire the Eiffel Tower"
                    3 -> "Walk around the Arc de Triomphe"
                    4 -> "Discover the Pantheon"
                    else -> "Unknown Step"
                },
                fontSize = 14.sp,
                color = Color.DarkGray
            )

            if (isExpanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = when (index) {
                        0 -> "Wheelchair-accessible entrance via Pyramid. Elevators available. Tactile guides for visually impaired visitors. Nearest accessible metro: Palais Royal – Musée du Louvre (150m)."
                        1 -> "Ramped access available on the south side. Interior access may be limited due to renovations. Nearest accessible metro: Saint-Michel Notre-Dame (300m)."
                        2 -> "Elevators to upper levels are wheelchair accessible. Priority access for visitors with disabilities. Nearest accessible metro: Bir-Hakeim (250m)."
                        3 -> "Great street-level views from Place Charles de Gaulle. No elevator access to rooftop. Nearest accessible metro: Charles de Gaulle – Étoile (200m)."
                        4 -> "Main entrance has ramped access. Interior adapted for wheelchair users. Tactile exhibits available. Nearest accessible metro: Cardinal Lemoine (180m)."
                        else -> "No accessibility details available."
                    },
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
        }
    }
}