package com.example.frontend.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack

@Composable
fun PrivacySettingsScreen(navController: NavHostController) {
    var isProfilePublic by remember { mutableStateOf(true) }
    var showSavedItineraries by remember { mutableStateOf(true) }
    var isTwoFactorEnabled by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { PrivacySettingsTopBar(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text("Privacy Settings", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(12.dp))

            // Profile Visibility Toggle
            SettingToggleItem(
                title = "Profile Visibility",
                description = if (isProfilePublic) "Public" else "Private",
                isChecked = isProfilePublic,
                onCheckedChange = { isProfilePublic = it }
            )

            // Show Saved Itineraries
            SettingToggleItem(
                title = "Show Saved Itineraries",
                description = if (showSavedItineraries) "Visible to others" else "Hidden",
                isChecked = showSavedItineraries,
                onCheckedChange = { showSavedItineraries = it }
            )

            SettingButtonItem(
                title = "Change Password",
                onClick = { navController.navigate("change_password") }
            )

            SettingToggleItem(
                title = "Enable Two-Factor Authentication",
                description = if (isTwoFactorEnabled) "Enabled" else "Disabled",
                isChecked = isTwoFactorEnabled,
                onCheckedChange = { isTwoFactorEnabled = it }
            )

            SettingButtonItem(
                title = "Privacy Policy",
                onClick = { navController.navigate("privacy_policy") }
            )

            SettingButtonItem(
                title = "Delete Account",
                isDestructive = true,
                onClick = { showDeleteDialog = true }
            )

            // Confirmation Dialog for Delete Account
            if (showDeleteDialog) {
                AlertDialog(
                    onDismissRequest = { showDeleteDialog = false },
                    title = { Text("Confirm Deletion") },
                    text = { Text("Are you sure you want to delete your account? This action cannot be undone.") },
                    confirmButton = {
                        TextButton(onClick = { /* Placeholder action */ showDeleteDialog = false }) {
                            Text("Delete", color = Color.Red)
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDeleteDialog = false }) {
                            Text("Cancel")
                        }
                    }
                )
            }
        }
    }
}

// ✅ Top Bar
@Composable
fun PrivacySettingsTopBar(navController: NavHostController) {
    TopAppBar(
        backgroundColor = Color.White,
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.Black)
            }
            Text("Privacy Settings", fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 8.dp))
        }
    }
}

// ✅ Toggle Setting Item
@Composable
fun SettingToggleItem(title: String, description: String, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = description, fontSize = 14.sp, color = Color.DarkGray)
        }
        Switch(checked = isChecked, onCheckedChange = onCheckedChange)
    }
    Divider(color = Color.LightGray, thickness = 1.dp)
}

// ✅ Button Setting Item
@Composable
fun SettingButtonItem(title: String, isDestructive: Boolean = false, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isDestructive) Color.Red else Color(0xFF3A6EA5),
            contentColor = Color.White
        )
    ) {
        Text(title, fontSize = 16.sp)
    }
}