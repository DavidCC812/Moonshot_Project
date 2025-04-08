package com.example.frontend.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.frontend.components.RecommendedDestinationCard

@Composable
fun SavedItinerariesScreen(navController: NavHostController, viewModel: SavedItinerariesViewModel) {
    Scaffold(
        topBar = { HomeTopBar(navController) },
        bottomBar = { BottomNavBar(navController, selectedScreen = "saved_itineraries") }
    ) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFFF8FAFC), Color(0xFFD9EAFD), Color(0xFFBCCCDC))
                    )
                )
                .padding(padding)
                .padding(horizontal = 16.dp),
            color = Color.Transparent
        ) {
            if (viewModel.savedItineraries.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "No itineraries saved yet!",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 12.dp)
                ) {
                    itemsIndexed(viewModel.savedItineraries) { index, itinerary ->
                        val (title, location) = itinerary

                        if (viewModel.savedItineraries.size == 1) {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                RecommendedDestinationCard(
                                    title = title,
                                    location = location,
                                    rating = 4.5,
                                    price = "Free",
                                    duration = "3h",
                                    people = 2,
                                    accessibilityFeatures = listOf(
                                        "Wheelchair Accessible",
                                        "Elevator Access"
                                    ),
                                    onClick = { navController.navigate("itinerary_details/$title/$location") },
                                    modifier = Modifier.width(250.dp)
                                )
                            }
                        } else {
                            if (index % 2 == 0) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    RecommendedDestinationCard(
                                        title = title,
                                        location = location,
                                        rating = 4.5,
                                        price = "Free",
                                        duration = "3h",
                                        people = 2,
                                        accessibilityFeatures = listOf(
                                            "Wheelchair Accessible",
                                            "Elevator Access"
                                        ),
                                        onClick = { navController.navigate("itinerary_details/$title/$location") },
                                        modifier = Modifier.weight(1f)
                                    )

                                    if (index + 1 < viewModel.savedItineraries.size) {
                                        val (nextTitle, nextLocation) = viewModel.savedItineraries[index + 1]
                                        RecommendedDestinationCard(
                                            title = nextTitle,
                                            location = nextLocation,
                                            rating = 4.5,
                                            price = "Free",
                                            duration = "3h",
                                            people = 2,
                                            accessibilityFeatures = listOf(
                                                "Wheelchair Accessible",
                                                "Elevator Access"
                                            ),
                                            onClick = { navController.navigate("itinerary_details/$nextTitle/$nextLocation") },
                                            modifier = Modifier.weight(1f)
                                        )
                                    } else {
                                        Spacer(modifier = Modifier.weight(1f))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}