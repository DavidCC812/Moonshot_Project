package com.example.frontend.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Notifications
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
import com.example.frontend.components.RecommendedDestinationCard
import com.example.frontend.components.NotificationBadge

@Composable
fun HomeScreen(navController: NavHostController, viewModel: SavedItinerariesViewModel) {
    Scaffold(
        topBar = { HomeTopBar(navController) },
        bottomBar = { BottomNavBar(navController, selectedScreen = "home") },
        backgroundColor = Color.Transparent
    ) { padding ->
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
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ) {

                NextPlanSection(navController, viewModel)
                Spacer(modifier = Modifier.height(16.dp))


                RecommendedDestinationsSection(navController)
                Spacer(modifier = Modifier.height(24.dp))

            }
        }
    }
}


@Composable
fun HomeTopBar(navController: NavHostController) {
    TopAppBar(
        backgroundColor = Color(0xFF9AA6B2),
        elevation = 4.dp,
        modifier = Modifier.height(56.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            // App Logo
            Text(
                text = "App Logo",
                fontSize = 18.sp,
                color = Color.Black
            )

            // Notifications Icon
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = { navController.navigate("notifications") }
                ) {
                    Box(contentAlignment = Alignment.TopEnd) {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = "Notifications",
                            tint = Color.Black,
                            modifier = Modifier.size(28.dp)
                        )
                        NotificationBadge(count = 1)
                    }
                }
            }
        }
    }
}


@Composable
fun BottomNavBar(navController: NavHostController, selectedScreen: String) {
    BottomAppBar(
        backgroundColor = Color(0xFF9AA6B2),
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
                icon = Icons.Filled.Bookmark,
                selected = selectedScreen == "itinerary",
                onClick = { navController.navigate("saved_itineraries") }
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
                tint = if (selected) Color.Black else Color(0xFF5F6B78),
                modifier = Modifier.size(32.dp)
            )
        } else if (iconResId != null) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                tint = if (selected) Color.Black else Color(0xFF5F6B78),
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun NextPlanSection(navController: NavHostController, viewModel: SavedItinerariesViewModel) {
    val nextPlan = viewModel.nextPlan.value

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Next Plan",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        if (nextPlan == null) {
            Text(
                text = "You have no reserved plans yet.",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            CustomButton(
                text = "Find Itineraries",
                onClick = { navController.navigate("search") },
                backgroundColor = Color(0xFF9AA6B2),
                textColor = Color.Black,
                buttonHeight = 40.dp,
                modifier = Modifier.fillMaxWidth(0.85f)
            )
        } else {
            RecommendedDestinationCard(
                title = nextPlan.first,
                location = nextPlan.second,
                rating = 4.5,
                price = "30",
                duration = "2 hours",
                people = 2,
                accessibilityFeatures = listOf("Wheelchair Accessible", "Braille Available"),
                onClick = { navController.navigate("itinerary_details/${nextPlan.first}/none") }
            )
        }
    }
}

@Composable
fun RecommendedDestinationsSection(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Text(
            text = "Recommended Destinations",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(start = 4.dp, end = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val recommendedDestinations = listOf(
                Triple("Louvre Museum", "Paris, France", 4.8) to "25",
                Triple("Orsay Museum", "Paris, France", 4.6) to "20",
                Triple("Eiffel Tower", "Paris, France", 4.9) to "30",
                Triple("Notre-Dame Cathedral", "Paris, France", 4.7) to "22",
                Triple("Arc de Triomphe", "Paris, France", 4.8) to "18",
                Triple("Palace of Versailles", "Paris, France", 4.9) to "35"
            )

            items(recommendedDestinations) { (info, price) ->
                val (name, location, rating) = info
                RecommendedDestinationCard(
                    title = name,
                    location = location,
                    rating = rating,
                    price = price,
                    duration = "2 hours",
                    people = 2,
                    accessibilityFeatures = listOf("Wheelchair Accessible", "Braille Available"),
                    onClick = { navController.navigate("itinerary_details/$name/none") }
                )
            }
        }
    }
}