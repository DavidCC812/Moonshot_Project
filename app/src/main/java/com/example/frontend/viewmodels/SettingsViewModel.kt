package com.example.frontend.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontend.models.UserSettingRequest
import com.example.frontend.models.UserSettingUi
import com.example.frontend.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(private val userId: String) : ViewModel() {

    // UI representation of user settings
    private val _settings = MutableStateFlow<List<UserSettingUi>>(emptyList())
    val settings: StateFlow<List<UserSettingUi>> = _settings

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadSettings()
    }

    // Load all settings and user's current preferences, combine for UI
    private fun loadSettings() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val allSettings = RetrofitClient.settingApi.getAllSettings()
                val userSettings = RetrofitClient.userSettingApi.getAllUserSettings()

                val settingMap = userSettings.associateBy { it.settingId }

                val uiList = allSettings.map { setting ->
                    val userSetting = settingMap[setting.id]
                    UserSettingUi(
                        id = userSetting?.id ?: "",
                        settingId = setting.id,
                        label = setting.label,
                        value = userSetting?.value ?: setting.defaultValue
                    )
                }

                _settings.value = uiList
            } catch (e: Exception) {
                _error.value = "Failed to load settings: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Create or update user setting based on existence
    fun postUserSetting(settingId: String, value: Boolean) {
        viewModelScope.launch {
            try {
                val existing = _settings.value.find { it.settingId == settingId }
                val userSettingId = existing?.id ?: ""
                val request = UserSettingRequest(
                    userId = userId,
                    settingId = settingId,
                    value = value
                )

                if (userSettingId.isNotEmpty()) {
                    RetrofitClient.userSettingApi.updateUserSetting(userSettingId, request)
                    Log.d("SettingsViewModel", "Updated setting via PUT: $settingId = $value")
                } else {
                    RetrofitClient.userSettingApi.postUserSetting(request)
                    Log.d("SettingsViewModel", "Created setting via POST: $settingId = $value")
                }

                // Update local UI state
                val updatedList = _settings.value.map {
                    if (it.settingId == settingId) it.copy(value = value) else it
                }
                _settings.value = updatedList

            } catch (e: Exception) {
                Log.e("SettingsViewModel", "Failed to post/update setting", e)
            }
        }
    }
}
