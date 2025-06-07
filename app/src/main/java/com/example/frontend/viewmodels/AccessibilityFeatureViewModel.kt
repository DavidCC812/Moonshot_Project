package com.example.frontend.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontend.models.AccessibilityFeature
import com.example.frontend.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class AccessibilityFeatureViewModel : ViewModel() {

    private val _features = MutableStateFlow<List<AccessibilityFeature>>(emptyList())
    val features: StateFlow<List<AccessibilityFeature>> = _features

    private val _selectedLabels = MutableStateFlow<List<String>>(emptyList())
    val selectedLabels: StateFlow<List<String>> = _selectedLabels

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        fetchAccessibilityFeatures()
    }

    private fun fetchAccessibilityFeatures() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = RetrofitClient.accessibilityFeatureApi.getAccessibilityFeatures()
                _features.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchUserFeatures(userId: String) {
        viewModelScope.launch {
            try {
                val userFeatures = RetrofitClient.userAccessibilityFeatureApi.getAllUserAccessibilityFeatures()
                val selectedIds = userFeatures
                    .filter { it.userId == UUID.fromString(userId) }
                    .map { it.featureId }
                    .toSet()

                val selectedLabels = _features.value
                    .filter { it.id in selectedIds }
                    .map { it.name }

                _selectedLabels.value = selectedLabels
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
