package com.example.frontend.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
fun ItineraryPlannerScreen(navController: NavHostController, viewModel: SavedItinerariesViewModel) {
    var newDestination by remember { mutableStateOf("") }
    val itineraryItems = remember { mutableStateListOf<String>() }

    val recommendedItineraries = listOf(
        "Classic Paris Tour" to listOf("Eiffel Tower", "Louvre Museum", "Notre-Dame Cathedral"),
        "Accessible Paris Tour" to listOf("Versailles Palace", "Orsay Museum", "Sainte-Chapelle"),
        "Historical Landmarks" to listOf("Arc de Triomphe", "Conciergerie", "Pantheon")
    )

    Scaffold(
        topBar = { HomeTopBar() },
        bottomBar = { BottomNavBar(navController, selectedScreen = "itinerary") }
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
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    "Create Itinerary",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 8.dp, top = 24.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(12.dp))
                        .padding(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BasicTextField(
                        value = newDestination,
                        onValueChange = { newDestination = it },
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp)
                            .padding(start = 8.dp),
                        singleLine = true
                    )

                    Button(
                        onClick = {
                            if (newDestination.isNotEmpty()) {
                                itineraryItems.add(newDestination)
                                newDestination = ""
                            }
                        },
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .height(40.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF3A6EA5),
                            contentColor = Color.White
                        )
                    ) {
                        Text("Add", fontSize = 14.sp)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(itineraryItems) { destination ->
                        ItineraryItem(destination, onRemove = { itineraryItems.remove(destination) })
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (itineraryItems.isNotEmpty()) {
                            val itineraryName = "Custom Itinerary (${itineraryItems.size} stops)"
                            viewModel.saveItinerary(itineraryName)
                            itineraryItems.clear()
                        }
                    },
                    enabled = itineraryItems.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF3A6EA5),
                        contentColor = Color.White
                    )
                ) {
                    Text("Save Itinerary")
                }

                Spacer(modifier = Modifier.height(24.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    item {
                        Text(
                            "Recommended Itineraries",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                    items(recommendedItineraries) { (title, destinations) ->
                        RecommendedItineraryCard(
                            title = title,
                            destinations = destinations,
                            onUse = { itineraryItems.addAll(destinations) },
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ItineraryItem(destination: String, onRemove: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(destination, fontSize = 16.sp)
            Button(
                onClick = onRemove,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF3A6EA5),
                    contentColor = Color.White
                )
            ) {
                Text("Remove")
            }
        }
    }
}

@Composable
fun RecommendedItineraryCard(title: String, destinations: List<String>, onUse: () -> Unit, navController: NavHostController) {
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
            Text(title, fontSize = 18.sp, fontWeight = FontWeight.Bold)

            destinations.forEach { destination ->
                Text("• $destination", fontSize = 14.sp, color = Color.DarkGray)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // View Itinerary Button
            Button(
                onClick = { navController.navigate("itinerary_details/$title/Next Stop Placeholder") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF3A6EA5),
                    contentColor = Color.White
                )
            ) {
                Text("View Itinerary")
            }

            Button(
                onClick = onUse,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF2C5F95),
                    contentColor = Color.White
                )
            ) {
                Text("Use This")
            }
        }
    }
}