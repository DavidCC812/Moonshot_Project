package com.example.frontend.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun SavedItinerariesScreen(navController: NavHostController, viewModel: SavedItinerariesViewModel) {
    Scaffold(
        topBar = { HomeTopBar() },
        bottomBar = { BottomNavBar(navController, selectedScreen = "profile") }
    ) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF3A6EA5), Color(0xFF5A92D5))
                    )
                )
                .padding(padding)
                .padding(horizontal = 16.dp),
            color = Color.Transparent
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Saved Itineraries",
                    fontSize = 22.sp,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                if (viewModel.savedItineraries.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "No itineraries saved yet!",
                            fontSize = 16.sp,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(viewModel.savedItineraries) { itinerary ->
                            SavedItineraryCard(itinerary, viewModel, navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SavedItineraryCard(itinerary: String, viewModel: SavedItinerariesViewModel, navController: NavHostController) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(itinerary, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { navController.navigate("itinerary_details/$itinerary/Next Stop Placeholder") },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF3A6EA5),
                        contentColor = Color.White
                    )
                ) {
                    Text("View", fontSize = 14.sp)
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = { viewModel.removeItinerary(itinerary) },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Red,
                        contentColor = Color.White
                    )
                ) {
                    Text("Remove", fontSize = 14.sp)
                }
            }
        }
    }
}