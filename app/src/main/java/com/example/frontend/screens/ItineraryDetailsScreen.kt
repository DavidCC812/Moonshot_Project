package com.example.frontend.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ItineraryDetailsScreen(navController: NavHostController, itineraryTitle: String) {
    var reviews by rememberSaveable {
        mutableStateOf(
            listOf(
                Review("Alice Johnson", "January 5, 2024", 5, "Amazing experience!", itineraryTitle),
                Review("Mark Smith", "December 20, 2023", 4, "Great itinerary, but could use more accessibility details.", itineraryTitle),
                Review("Emily Brown", "December 10, 2023", 3, "Good, but some places were not as accessible as advertised.", itineraryTitle)
            )
        )
    }

    val navBackStackEntry = navController.currentBackStackEntry
    LaunchedEffect(navBackStackEntry) {
        val newName = navBackStackEntry?.savedStateHandle?.get<String>("review_name")
        val newDate = navBackStackEntry?.savedStateHandle?.get<String>("review_date")
        val newRating = navBackStackEntry?.savedStateHandle?.get<Int>("review_rating")
        val newText = navBackStackEntry?.savedStateHandle?.get<String>("review_text")

        if (newName != null && newDate != null && newRating != null && newText != null) {
            val newReview = Review(newName, newDate, newRating, newText, itineraryTitle)

            if (!reviews.contains(newReview)) {
                reviews = listOf(newReview) + reviews
            }

            // Avoid duplicate additions
            navBackStackEntry.savedStateHandle.remove<String>("review_name")
            navBackStackEntry.savedStateHandle.remove<String>("review_date")
            navBackStackEntry.savedStateHandle.remove<Int>("review_rating")
            navBackStackEntry.savedStateHandle.remove<String>("review_text")
        }
    }

    Scaffold(
        topBar = { HomeTopBar() },
        bottomBar = { BottomNavBar(navController, selectedScreen = "search") }
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
                modifier = Modifier.fillMaxSize(),
            ) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                ) {
                    item {
                        Column {
                            Text(
                                text = itineraryTitle,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .background(Color.LightGray),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("Map/Image Placeholder", fontSize = 16.sp, color = Color.Gray)
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Explore the beauty of $itineraryTitle with full accessibility support. Enjoy an experience with well-defined paths and the necessary accommodations.",
                                fontSize = 16.sp,
                                color = Color.White,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )
                        }
                    }

                    item { AccessibilityInfo() }
                    item { GeneralInfoSection() }
                    item { ItineraryStepsSection() }
                    item { AlternativeRoutesSection() }

                    item {
                        Column(modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)) {
                            Text(
                                "Reviews",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )

                            Button(
                                onClick = { navController.navigate("write_review/$itineraryTitle") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                                    .padding(vertical = 8.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color(0xFF3A6EA5),
                                    contentColor = Color.White
                                )
                            ) {
                                Icon(Icons.Filled.Star, contentDescription = "Write Review", tint = Color.White)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Write a Review", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }

                    items(reviews) { review ->
                        ReviewCard(review, navController, showItineraryButton = false)
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Button(
                        onClick = { /* TODO: Implement navigation logic */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF3A6EA5),
                            contentColor = Color.White
                        )
                    ) {
                        Icon(Icons.Filled.PlayArrow, contentDescription = "Navigate", modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("Start Navigation", fontSize = 18.sp, fontWeight = FontWeight.Bold, maxLines = 1)
                    }
                }
            }
        }
    }
}

@Composable
fun AccessibilityInfo() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            "Accessibility Overview",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AccessibilityTag("Wheelchair Accessible ✅", Color(0xFFDFF6DD))
            AccessibilityTag("Hearing Aid Support ✅", Color(0xFFDFF6DD))
            AccessibilityTag("Elevator ❌", Color(0xFFFFD6D6))
        }
    }
}

@Composable
fun AccessibilityTag(label: String, backgroundColor: Color) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        backgroundColor = backgroundColor,
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp
    ) {
        Box(
            modifier = Modifier.padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = label,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
    }
}
@Composable
fun GeneralInfoSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            "General Information",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        GeneralInfoItem("⏳", "Duration", "Approx. 2-3 hours")
        GeneralInfoItem("🚕", "Transport", "Accessible taxis, metro available")
        GeneralInfoItem("❌", "Cancellation", "Free cancellation up to 24 hours before the trip")
    }
}

@Composable
fun GeneralInfoItem(icon: String, title: String, detail: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = 2.dp,
        backgroundColor = Color(0xFFF5F5F5)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = icon,
                fontSize = 18.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = detail,
                fontSize = 16.sp,
                color = Color.DarkGray,
                modifier = Modifier.weight(2f)
            )
        }
    }
}

@Composable
fun ItineraryStepsSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            "What You'll Do",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        val steps = listOf(
            Pair("Arrive at Paris", "✈️"),
            Pair("Visit the Louvre Museum", "🏛️"),
            Pair("Lunch at an accessible restaurant", "🍽️"),
            Pair("Take a scenic wheelchair-friendly route", "🛤️"),
            Pair("Return to hotel with accessible transport", "🏨")
        )

        Column {
            steps.forEach { (step, icon) ->
                ItineraryStepCard(step, icon)
            }
        }
    }
}

@Composable
fun ItineraryStepCard(step: String, icon: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = 2.dp,
        backgroundColor = Color(0xFFF5F5F5)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = icon,
                fontSize = 18.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = step,
                fontSize = 16.sp,
                color = Color.Black
            )
        }
    }
}
@Composable
fun AlternativeRoutesSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            "Alternative Accessible Routes",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        val routes = listOf(
            Pair("Wheelchair-accessible pathway via Rue de Rivoli", "🦽"),
            Pair("Alternative elevator access at the museum", "🛗"),
            Pair("Nearby tram service with wheelchair access", "🚋")
        )

        Column {
            routes.forEach { (route, icon) ->
                AlternativeRouteCard(route, icon)
            }
        }
    }
}

@Composable
fun AlternativeRouteCard(route: String, icon: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = 2.dp,
        backgroundColor = Color(0xFFE8F5E9)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = icon,
                fontSize = 18.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = route,
                fontSize = 16.sp,
                color = Color.Black
            )
        }
    }
}

@Composable
fun ReviewCard(review: Review, navController: NavHostController, showItineraryButton: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp),
        backgroundColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = review.userName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = review.date,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Star Rating
            Row {
                repeat(review.rating) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Star",
                        tint = Color(0xFFFFD700),
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Review Text
            Text(
                text = review.text,
                fontSize = 14.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Divider(color = Color.LightGray, thickness = 1.dp)

            Spacer(modifier = Modifier.height(8.dp))

            if (showItineraryButton) {
                Button(
                    onClick = {
                        navController.navigate("itinerary_details/${review.itineraryTitle}/Next Stop Placeholder")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF3A6EA5),
                        contentColor = Color.White
                    )
                ) {
                    Text("View Itinerary", fontSize = 16.sp)
                }
            }
        }
    }
}

data class Review(
    val userName: String,
    val date: String,
    val rating: Int,
    val text: String,
    val itineraryTitle: String
)