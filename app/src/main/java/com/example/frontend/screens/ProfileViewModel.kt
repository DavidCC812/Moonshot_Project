package com.example.frontend.screens

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf

class ProfileViewModel : ViewModel() {
    var name = mutableStateOf("John Doe")
    var email = mutableStateOf("johndoe@example.com")
    var phone = mutableStateOf("+123 456 789")
    var accessibility = mutableStateOf("Wheelchair Accessible")

    fun updateProfile(newName: String, newEmail: String, newPhone: String, newAccessibility: String) {
        name.value = newName
        email.value = newEmail
        phone.value = newPhone
        accessibility.value = newAccessibility
    }
}