package com.example.frontend.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.ui.text.buildAnnotatedString
import kotlinx.coroutines.launch

@Composable
fun ItineraryDetailsScreen(
    navController: NavHostController,
    itineraryTitle: String,
    viewModel: SavedItinerariesViewModel
) {
    var reviews by rememberSaveable {
        mutableStateOf(
            listOf(
                Review(
                    "Alice Johnson",
                    "January 5, 2024",
                    5,
                    "Amazing experience!",
                    itineraryTitle
                ),
                Review(
                    "Mark Smith",
                    "December 20, 2023",
                    4,
                    "Great itinerary, but could use more accessibility details.",
                    itineraryTitle
                ),
                Review(
                    "Emily Brown",
                    "December 10, 2023",
                    3,
                    "Good, but some places were not as accessible as advertised.",
                    itineraryTitle
                )
            )
        )
    }

    val isSaved =
        remember { mutableStateOf(viewModel.savedItineraries.any { it.first == itineraryTitle }) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = { HomeTopBar(navController) },
        bottomBar = { BottomNavBar(navController, selectedScreen = "search") },
        snackbarHost = { SnackbarHost(snackbarHostState) }
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
                .padding(padding)
                .padding(horizontal = 16.dp),
            color = Color.Transparent
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                item {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = itineraryTitle,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )

                            IconButton(
                                onClick = {
                                    if (isSaved.value) {
                                        viewModel.removeItinerary(itineraryTitle to "Paris")
                                        isSaved.value = false
                                        coroutineScope.launch {
                                            snackbarHostState.showSnackbar("Removed from Saved Itineraries")
                                        }
                                    } else {
                                        viewModel.saveItinerary(itineraryTitle to "Paris")
                                        isSaved.value = true
                                        coroutineScope.launch {
                                            snackbarHostState.showSnackbar("Saved to Itineraries")
                                        }
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = if (isSaved.value) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                                    contentDescription = "Save Itinerary",
                                    tint = Color.Black
                                )
                            }
                        }

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
                            text = buildAnnotatedString {
                                append("This itinerary lasts approximately ")
                                boldText("2-3 hours")
                                append(", offering a fully accessible experience. ")
                                append("Visitors can rely on ")
                                boldText("accessible taxis and metro services")
                                append(" to reach key destinations. The itinerary also provides ")
                                boldText("free cancellation")
                                append(" up to ")
                                boldText("24 hours before the trip")
                                append(" if needed.\n\n")

                                append("For those requiring additional accessibility options, ")
                                boldText("alternative routes")
                                append(" include a ")
                                boldText("wheelchair-accessible pathway via Rue de Rivoli")
                                append(", ")
                                boldText("elevator access at the museum")
                                append(", and ")
                                boldText("nearby tram services with wheelchair access")
                                append(".")
                            },
                            fontSize = 16.sp,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(20.dp))


                        Button(
                            onClick = {
                                viewModel.setAsNextPlan(itineraryTitle to "Paris")
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Set as Next Plan")
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFF9AA6B2),
                                contentColor = Color.Black
                            )
                        ) {
                            Text("Set as Next Plan", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                item { AccessibilityInfo() }
                item { DetailsNavigationRow(navController, itineraryTitle) }

                item {
                    Column(modifier = Modifier.padding(top = 12.dp, bottom = 8.dp)) {
                        Text(
                            "Reviews",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
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
                                backgroundColor = Color(0xFF9AA6B2),
                                contentColor = Color.Black
                            )
                        ) {
                            Icon(
                                Icons.Filled.Star,
                                contentDescription = "Write Review",
                                tint = Color.Black
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Write a Review", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                items(reviews) { review ->
                    ReviewCard(review, navController, showItineraryButton = false)
                }
            }
        }
    }
}

fun AnnotatedString.Builder.boldText(text: String) {
    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
        append(text)
    }
}

@Composable
fun AccessibilityInfo() {
    var isExpanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Accessibility Overview",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = if (isExpanded) Icons.Filled.KeyboardArrowDown else Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "Expand/Collapse",
            modifier = Modifier.size(24.dp)
        )
    }

    if (isExpanded) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
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
fun DetailsNavigationRow(navController: NavHostController, itineraryTitle: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate("itinerary_steps/$itineraryTitle") }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Details",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "Navigate to Details",
            modifier = Modifier.size(24.dp)
        )
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
                        backgroundColor = Color(0xFF9AA6B2),
                        contentColor = Color.Black
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