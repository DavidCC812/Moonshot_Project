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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.frontend.components.CustomButton

@Composable
fun SearchScreen(navController: NavHostController, viewModel: SavedItinerariesViewModel) {
    var selectedFilter by remember { mutableStateOf("All") }
    val accessibilityFilters = listOf("All", "Wheelchair Accessible", "Braille Available", "Hearing Aid")

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
                .padding(horizontal = 16.dp),
            color = Color.Transparent
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Text(
                    text = "Search Destinations",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp, start = 8.dp, bottom = 8.dp)
                )

                SearchBar(
                    selectedFilter = selectedFilter,
                    onFilterChange = { selectedFilter = it },
                    accessibilityFilters = accessibilityFilters
                )

                Spacer(modifier = Modifier.height(16.dp))

                SearchResults(selectedFilter, viewModel, navController)
            }
        }
    }
}

@Composable
fun SearchBar(
    selectedFilter: String,
    onFilterChange: (String) -> Unit,
    accessibilityFilters: List<String>,
) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White.copy(alpha = 0.2f))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = android.R.drawable.ic_menu_search),
            contentDescription = "Search Icon",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        BasicTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.weight(1f),
            textStyle = LocalTextStyle.current.copy(fontSize = 16.sp, color = Color.White),
            singleLine = true,
            decorationBox = { innerTextField ->
                Box(modifier = Modifier.fillMaxWidth()) {
                    if (searchQuery.text.isEmpty()) {
                        Text("Search destinations...", fontSize = 16.sp, color = Color.White.copy(alpha = 0.6f))
                    }
                    innerTextField()
                }
            }
        )

        Box {
            IconButton(onClick = { expanded = true }) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_sort_by_size),
                    contentDescription = "Filter",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                accessibilityFilters.forEach { filter ->
                    DropdownMenuItem(onClick = {
                        onFilterChange(filter)
                        expanded = false
                    }) {
                        Text(filter)
                    }
                }
            }
        }
    }
}

@Composable
fun SearchResults(selectedFilter: String, viewModel: SavedItinerariesViewModel, navController: NavHostController) {
    val allDestinations = listOf(
        "Eiffel Tower" to listOf("Wheelchair Accessible", "Elevator"),
        "Louvre Museum" to listOf("Wheelchair Accessible", "Braille Available"),
        "Notre-Dame Cathedral" to listOf("Limited Accessibility"),
        "Versailles Palace" to listOf("Wheelchair Accessible", "Guided Tours"),
        "Orsay Museum" to listOf("Wheelchair Accessible", "Braille Available", "Hearing Aid")
    )

    val filteredDestinations = if (selectedFilter == "All") {
        allDestinations
    } else {
        allDestinations.filter { it.second.contains(selectedFilter) }
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(filteredDestinations) { (name, tags) ->
            SearchDestinationCard(name, tags, viewModel, navController)
        }
    }
}

@Composable
fun SearchDestinationCard(
    name: String,
    tags: List<String>,
    viewModel: SavedItinerariesViewModel,
    navController: NavHostController
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(name, fontSize = 18.sp, fontWeight = FontWeight.Bold)

            tags.forEach { tag ->
                Text(text = "• $tag", fontSize = 14.sp, color = Color.DarkGray)
            }

            CustomButton(
                text = "View Itinerary",
                onClick = { navController.navigate("itinerary_details/$name/Next Stop Placeholder") },
                buttonHeight = 45.dp,
                modifier = Modifier.fillMaxWidth()
            )

            CustomButton(
                text = "Save",
                onClick = { viewModel.saveItinerary(name) },
                buttonHeight = 45.dp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}