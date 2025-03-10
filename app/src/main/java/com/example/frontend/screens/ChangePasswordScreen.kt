package com.example.frontend.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ChangePasswordScreen(navController: NavHostController) {
    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var oldPasswordError by remember { mutableStateOf("") }
    var newPasswordError by remember { mutableStateOf("") }
    var confirmPasswordError by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()
    var successMessage by remember { mutableStateOf<String?>(null) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(48.dp))

            // App Logo Placeholder
            Text(
                text = "App Logo",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(48.dp))

            Text(
                text = "Change Your Password",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Enter your current password and set a new one.",
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Input Fields
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                PasswordInputField(
                    label = "Enter current password",
                    password = oldPassword,
                    onPasswordChange = { oldPassword = it; oldPasswordError = "" },
                    errorText = oldPasswordError
                )

                PasswordInputField(
                    label = "Enter new password",
                    password = newPassword,
                    onPasswordChange = { newPassword = it; newPasswordError = "" },
                    errorText = newPasswordError
                )

                PasswordInputField(
                    label = "Confirm new password",
                    password = confirmPassword,
                    onPasswordChange = { confirmPassword = it; confirmPasswordError = "" },
                    errorText = confirmPasswordError
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Change Password Button
            Button(
                onClick = {
                    var isValid = true

                    if (oldPassword.isBlank()) {
                        oldPasswordError = "Current password is required"
                        isValid = false
                    }

                    if (newPassword.isBlank()) {
                        newPasswordError = "New password is required"
                        isValid = false
                    } else if (newPassword.length < 6) {
                        newPasswordError = "Password must be at least 6 characters"
                        isValid = false
                    }

                    if (confirmPassword.isBlank()) {
                        confirmPasswordError = "Please confirm your new password"
                        isValid = false
                    } else if (confirmPassword != newPassword) {
                        confirmPasswordError = "Passwords do not match"
                        isValid = false
                    }

                    if (isValid) {
                        successMessage = "Password changed successfully!"
                        coroutineScope.launch {
                            delay(2000) // Simulate a success message
                            navController.popBackStack() // Go back to Privacy Settings
                        }
                        // TODO: Implement actual password change logic in backend
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF3A6EA5), contentColor = Color.White)
            ) {
                Text("Change Password", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            successMessage?.let {
                Text(
                    it,
                    color = Color(0xFF3A6EA5),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Cancel Button
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.width(150.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFD9534F), contentColor = Color.White)
            ) {
                Text("Cancel", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun PasswordInputField(
    label: String,
    password: String,
    onPasswordChange: (String) -> Unit,
    errorText: String
) {
    Column {
        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text(label, color = Color.DarkGray) },
            isError = errorText.isNotEmpty(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF3A6EA5),
                unfocusedBorderColor = Color(0xFF3A6EA5),
                textColor = Color.DarkGray,
                cursorColor = Color(0xFF3A6EA5),
                errorBorderColor = Color.Red
            ),
            shape = RoundedCornerShape(8.dp)
        )

        if (errorText.isNotEmpty()) {
            Text(
                text = errorText,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }
    }
}