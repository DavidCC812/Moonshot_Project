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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person

@Composable
fun ProfileScreen(navController: NavHostController, profileViewModel: ProfileViewModel) {
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
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfileHeader(profileViewModel)
                AccountDetails(profileViewModel)
                ActivitySection(navController)
                SettingsSection(navController)
                LogoutButton(navController)
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

        Text(text = profileViewModel.name.value, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Text(text = "Traveler & Explorer", fontSize = 14.sp, color = Color.LightGray)
    }
}

@Composable
fun AccountDetails(profileViewModel: ProfileViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Text("Account Information", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)

        Spacer(modifier = Modifier.height(8.dp))

        ProfileInfoItem("Email", profileViewModel.email.value)
        ProfileInfoItem("Phone", profileViewModel.phone.value)
        ProfileInfoItem("Accessibility Preferences", profileViewModel.accessibility.value)
    }
}

@Composable
fun ProfileInfoItem(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(text = label, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
        Text(text = value, fontSize = 16.sp, color = Color.Black)
        Divider(modifier = Modifier.padding(vertical = 4.dp), thickness = 1.dp, color = Color.LightGray)
    }
}

@Composable
fun ActivitySection(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Text("My Activity", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)

        Spacer(modifier = Modifier.height(8.dp))

        ActivityItem("Saved Itineraries", onClick = { navController.navigate("saved_itineraries") })
        ActivityItem("Reviews", onClick = { navController.navigate("my_reviews")})
    }
}

@Composable
fun ActivityItem(title: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF3A6EA5), contentColor = Color.White)
    ) {
        Text(title, fontSize = 16.sp)
    }
}

@Composable
fun SettingsSection(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Text("Settings", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)

        Spacer(modifier = Modifier.height(8.dp))

        SettingsItem(Icons.Filled.Edit, "Edit Profile") { navController.navigate("edit_profile") }
        SettingsItem(Icons.Filled.Notifications, "Notifications") { navController.navigate("notifications") }
        SettingsItem(Icons.Filled.Person, "Privacy Settings") { navController.navigate("privacy_settings") }
    }
}

@Composable
fun SettingsItem(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFEFEFEF), RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = title, tint = Color(0xFF3A6EA5), modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Text(title, fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF673AB7), contentColor = Color.White)
        ) {
            Text("Go")
        }
    }
}

@Composable
fun LogoutButton(navController: NavHostController) {
    Button(
        onClick = { navController.navigate("welcome") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red, contentColor = Color.White)
    ) {
        Icon(imageVector = Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Logout", modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text("Logout", fontSize = 16.sp)
    }
}