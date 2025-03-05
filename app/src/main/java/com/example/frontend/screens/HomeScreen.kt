package com.example.frontend.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.frontend.components.CustomButton
import com.example.frontend.components.DiscussionCard
import com.example.frontend.components.RecommendedDestinationCard

@Composable
fun HomeScreen(navController: NavHostController, viewModel: SavedItinerariesViewModel) {
    Scaffold(
        topBar = { HomeTopBar() },
        bottomBar = { BottomNavBar(navController, selectedScreen = "home") },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("notifications") },
                backgroundColor = Color(0xFF3A6EA5),
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
            }
        },
        backgroundColor = Color.Transparent
    ) { padding ->
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
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ) {
                RecommendedDestinationsSection(viewModel)
                Spacer(modifier = Modifier.height(16.dp))

                TrendingDestinationsSection(navController)

                Spacer(modifier = Modifier.height(24.dp))

                CustomButton(
                    text = "View More",
                    onClick = { navController.navigate("search") },
                    backgroundColor = Color(0xFF3A6EA5),
                    textColor = Color.White,
                    buttonHeight = 50.dp,
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}

@Composable
fun HomeTopBar() {
    TopAppBar(
        backgroundColor = Color(0xFF2A5D9F),
        elevation = 4.dp,
        modifier = Modifier.height(56.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "App Logo",
                fontSize = 18.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun BottomNavBar(navController: NavHostController, selectedScreen: String) {
    BottomAppBar(
        backgroundColor = Color(0xFF2A5D9F),
        elevation = 8.dp,
        modifier = Modifier.height(60.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavButton(
                icon = Icons.Filled.Home,
                selected = selectedScreen == "home",
                onClick = { navController.navigate("home") }
            )

            BottomNavButton(
                iconResId = android.R.drawable.ic_menu_search,
                selected = selectedScreen == "search",
                onClick = { navController.navigate("search") }
            )

            BottomNavButton(
                iconResId = android.R.drawable.ic_menu_myplaces,
                selected = selectedScreen == "itinerary",
                onClick = { navController.navigate("itinerary_planner") }
            )
            BottomNavButton(
                icon = Icons.Filled.Person,
                selected = selectedScreen == "profile",
                onClick = { navController.navigate("profile") }
            )
        }
    }
}

@Composable
fun BottomNavButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector? = null,
    iconResId: Int? = null,
    selected: Boolean,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (selected) Color.White else Color.Gray,
                modifier = Modifier.size(32.dp)
            )
        } else if (iconResId != null) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                tint = if (selected) Color.White else Color.Gray,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun RecommendedDestinationsSection(viewModel: SavedItinerariesViewModel) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
    ) {
        Text(
            text = "Recommended Destinations",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(start = 4.dp, end = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val destinations = listOf(
                "Louvre Museum" to listOf("Wheelchair Accessible", "Braille Available"),
                "Orsay Museum" to listOf("Wheelchair Accessible", "Braille Available", "Hearing aid"),
                "Eiffel Tower" to listOf("Wheelchair Accessible", "Guided Tours")
            )

            items(destinations) { (name, badges) ->
                RecommendedDestinationCard(
                    title = name,
                    accessibilityFeatures = badges,
                    onSaveClick = { viewModel.saveItinerary(name) }
                )
            }
        }
    }
}


@Composable
fun TrendingDestinationsSection(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        Text(
            text = "Trending Destinations",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        val trendingDestinations = listOf(
            "Eiffel Tower" to "Wheelchair Accessible - Guided Tours",
            "Arc de Triomphe" to "Audio Assistance - Elevator Access",
            "Notre-Dame Cathedral" to "Braille Signs - Hearing Aid Support"
        )

        for ((title, features) in trendingDestinations) {
            DiscussionCard(
                title = title,
                authorComments = features,
                onJoinClick = { navController.navigate("search") }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}