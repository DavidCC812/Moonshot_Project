package com.example.frontend.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
fun MyReviewsScreen(navController: NavHostController, myReviewsViewModel: MyReviewsViewModel) {
    val myReviews by remember { mutableStateOf(myReviewsViewModel.myReviews) }

    Scaffold(
        topBar = { HomeTopBar() },
        bottomBar = { BottomNavBar(navController, selectedScreen = "profile") },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val defaultItinerary = "General Review"
                    navController.navigate("write_review/$defaultItinerary")
                },
                backgroundColor = Color(0xFF3A6EA5),
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, contentDescription = "New Review")
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
                .padding(padding)
                .padding(horizontal = 16.dp),
            color = Color.Transparent
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    "My Reviews",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )

                if (myReviews.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No reviews yet!", fontSize = 16.sp, color = Color.White)
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(myReviews) { review ->
                            ReviewCard(review, navController, showItineraryButton = true)
                        }
                    }
                }
            }
        }
    }
}