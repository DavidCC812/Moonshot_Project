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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun EditProfileScreen(navController: NavHostController, profileViewModel: ProfileViewModel) {
    var name by remember { mutableStateOf(profileViewModel.name.value) }
    var email by remember { mutableStateOf(profileViewModel.email.value) }
    var phone by remember { mutableStateOf(profileViewModel.phone.value) }
    var accessibility by remember { mutableStateOf(profileViewModel.accessibility.value) }

    Scaffold(
        topBar = { EditProfileTopBar(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EditProfileHeader()
            EditableAccountDetails(
                name, email, phone, accessibility,
                onNameChange = { name = it },
                onEmailChange = { email = it },
                onPhoneChange = { phone = it },
                onAccessibilityChange = { accessibility = it }
            )
            SaveChangesButton(navController, profileViewModel, name, email, phone, accessibility)
        }
    }
}

@Composable
fun EditProfileTopBar(navController: NavHostController) {
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
            Text("Edit Profile", fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 8.dp))
        }
    }
}

@Composable
fun EditableAccountDetails(
    name: String, email: String, phone: String, accessibility: String,
    onNameChange: (String) -> Unit, onEmailChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit, onAccessibilityChange: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text("Edit Account Information", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        EditableProfileInfoItem("Name", name, onNameChange)
        EditableProfileInfoItem("Email", email, onEmailChange)
        EditableProfileInfoItem("Phone", phone, onPhoneChange)
        EditableProfileInfoItem("Accessibility Preferences", accessibility, onAccessibilityChange)
    }
}

@Composable
fun EditableProfileInfoItem(label: String, value: String, onValueChange: (String) -> Unit) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(text = label, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color(0xFFF5F5F5))
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun SaveChangesButton(
    navController: NavHostController,
    profileViewModel: ProfileViewModel,
    name: String, email: String, phone: String, accessibility: String
) {
    Button(
        onClick = {
            profileViewModel.updateProfile(name, email, phone, accessibility)
            navController.popBackStack()
        },
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF3A6EA5), contentColor = Color.White)
    ) {
        Text("Save Changes", fontSize = 16.sp)
    }
}

@Composable
fun EditProfileHeader() {
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

        Text(text = "John Doe", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text(text = "Traveler & Explorer", fontSize = 14.sp, color = Color.Gray)
    }
}