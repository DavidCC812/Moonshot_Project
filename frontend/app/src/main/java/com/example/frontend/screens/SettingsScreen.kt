package com.example.frontend.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.text.input.KeyboardType
import com.example.frontend.components.CustomInputField

@Composable
fun SettingsScreen(navController: NavHostController, profileViewModel: ProfileViewModel) {
    val name by remember { mutableStateOf(profileViewModel.name.value) }
    var email by remember { mutableStateOf(profileViewModel.email.value) }
    var phone by remember { mutableStateOf(profileViewModel.phone.value) }
    var accessibility by remember { mutableStateOf(profileViewModel.accessibility.value) }
    var isProfilePublic by remember { mutableStateOf(true) }
    var showSavedItineraries by remember { mutableStateOf(true) }
    var isTwoFactorEnabled by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { SettingsTopBar(navController) }
    ) { padding ->
        Column(
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
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileHeader()
            EditableAccountDetails(
                name = name,
                email = email,
                phone = phone,
                accessibility = accessibility,
                onEmailChange = { email = it },
                onPhoneChange = { phone = it },
                onAccessibilityChange = { accessibility = it }
            )
            Divider(modifier = Modifier.padding(vertical = 12.dp))

            SettingToggleItem(
                "Profile Visibility",
                if (isProfilePublic) "Public" else "Private",
                isProfilePublic
            ) { isProfilePublic = it }
            SettingToggleItem(
                "Show Saved Itineraries",
                if (showSavedItineraries) "Visible to others" else "Hidden",
                showSavedItineraries
            ) { showSavedItineraries = it }
            SettingButtonItem("Change Password") { navController.navigate("change_password") }
            SettingToggleItem(
                "Enable Two-Factor Authentication",
                if (isTwoFactorEnabled) "Enabled" else "Disabled",
                isTwoFactorEnabled
            ) { isTwoFactorEnabled = it }
            SettingButtonItem("Privacy Policy") { navController.navigate("privacy_policy") }
            SettingButtonItem("Delete Account", isDestructive = true) { showDeleteDialog = true }
        }
    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Confirm Deletion") },
            text = { Text("Are you sure you want to delete your account? This action cannot be undone.") },
            confirmButton = {
                TextButton(onClick = { /* Add delete logic */ showDeleteDialog = false }) {
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

@Composable
fun SettingsTopBar(navController: NavHostController) {
    TopAppBar(
        backgroundColor = Color(0xFF9AA6B2),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
            Text("Settings", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(48.dp))
        }
    }
}

@Composable
fun ProfileHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color.Gray)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("John Doe", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text("Traveler & Explorer", fontSize = 14.sp, color = Color.Gray)
    }
}

@Composable
fun SettingToggleItem(
    title: String,
    description: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
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

@Composable
fun SettingButtonItem(title: String, isDestructive: Boolean = false, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isDestructive) Color(0xFFD32F2F) else Color(0xFF9AA6B2),
            contentColor = Color.Black
        )
    ) {
        Text(title, fontSize = 16.sp)
    }
}

@Composable
fun EditableAccountDetails(
    name: String, email: String, phone: String, accessibility: String,
    onEmailChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onAccessibilityChange: (String) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Text("Edit Account Information", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        CustomInputField(
            value = name,
            onValueChange = {},
            label = "Name",
            isError = false,
            keyboardType = KeyboardType.Text,
            enabled = false
        )
        Spacer(modifier = Modifier.height(8.dp))

        CustomInputField(
            value = email,
            onValueChange = onEmailChange,
            label = "Email",
            isError = false,
            keyboardType = KeyboardType.Email
        )
        Spacer(modifier = Modifier.height(8.dp))

        CustomInputField(
            value = phone,
            onValueChange = onPhoneChange,
            label = "Phone",
            isError = false,
            keyboardType = KeyboardType.Phone
        )
        Spacer(modifier = Modifier.height(8.dp))

        CustomInputField(
            value = accessibility,
            onValueChange = onAccessibilityChange,
            label = "Accessibility Preferences",
            isError = false,
            keyboardType = KeyboardType.Text
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}