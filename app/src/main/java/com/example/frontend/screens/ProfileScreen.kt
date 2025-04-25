package com.example.frontend.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import com.example.frontend.components.NotificationBadge

@Composable
fun ProfileScreen(navController: NavHostController, profileViewModel: ProfileViewModel) {
    Scaffold(
        topBar = { ProfileTopBar(navController) },
        bottomBar = { BottomNavBar(navController, selectedScreen = "profile") }
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfileHeader(profileViewModel)

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = 6.dp,
                    shape = RoundedCornerShape(12.dp),
                    backgroundColor = Color.White
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        AccountDetails(profileViewModel)
                        Divider(modifier = Modifier.padding(vertical = 8.dp))

                        ActivitySection(navController)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                LogoutButton(navController)
            }
        }
    }
}

@Composable
fun ProfileTopBar(navController: NavHostController) {
    TopAppBar(
        backgroundColor = Color(0xFF9AA6B2),
        elevation = 4.dp,
        modifier = Modifier.height(56.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "App Logo",
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )


            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Notifications Icon
                IconButton(onClick = { navController.navigate("notifications") }) {
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

                // Settings Icon
                IconButton(onClick = { navController.navigate("settings") }) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "Settings",
                        tint = Color.Black,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileHeader(profileViewModel: ProfileViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
                .background(Color.Gray)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = profileViewModel.name.value,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(text = "Traveler & Explorer", fontSize = 14.sp, color = Color.LightGray)
    }
}

@Composable
fun AccountDetails(profileViewModel: ProfileViewModel) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            "Account Information",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        val profileItems = listOf(
            "Email" to profileViewModel.email.value,
            "Phone" to profileViewModel.phone.value,
            "Accessibility Preferences" to profileViewModel.accessibility.value
        )

        profileItems.forEachIndexed { index, (label, value) ->
            ProfileInfoItem(label, value, showDivider = index < profileItems.lastIndex)
        }
    }
}

@Composable
fun ProfileInfoItem(label: String, value: String, showDivider: Boolean) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(text = label, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
        Text(text = value, fontSize = 16.sp, color = Color.Black)
        if (showDivider) {
            Divider(
                modifier = Modifier.padding(vertical = 4.dp),
                thickness = 1.dp,
                color = Color.LightGray
            )
        }
    }
}

@Composable
fun ActivitySection(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text("My Activity", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)

        Spacer(modifier = Modifier.height(8.dp))

        ActivityItem("Reviews", onClick = { navController.navigate("my_reviews") })
    }
}

@Composable
fun ActivityItem(title: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF9AA6B2),
            contentColor = Color.Black
        )
    ) {
        Text(title, fontSize = 16.sp)
    }
}

@Composable
fun LogoutButton(navController: NavHostController) {
    Button(
        onClick = { navController.navigate("welcome") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(45.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Red,
            contentColor = Color.White
        )
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
            contentDescription = "Logout",
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text("Logout", fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}