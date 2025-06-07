package com.example.frontend.viewmodels

import androidx.lifecycle.viewModelScope
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.frontend.models.User
import com.example.frontend.models.UserRequest
import com.example.frontend.network.RetrofitClient
import com.example.frontend.storage.TokenManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.util.Log
import com.example.frontend.models.LoginRequest
import kotlinx.coroutines.flow.firstOrNull

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun createUser(request: UserRequest) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = RetrofitClient.userApi.createUser(request)
                _user.value = response
            } catch (e: Exception) {
                _error.value = e.localizedMessage ?: "Unexpected error"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchUser(id: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = RetrofitClient.userApi.getUserById(id)
                _user.value = response
            } catch (e: Exception) {
                _error.value = e.localizedMessage ?: "Unable to load user"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchUserByEmail(email: String, onResult: (User?) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = RetrofitClient.userApi.getUserByEmail(email)
                _user.value = response
                onResult(response)
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error fetching user by email: ${e.localizedMessage}", e)
                _user.value = null
                onResult(null)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchUserByPhone(phone: String, onResult: (User?) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = RetrofitClient.userApi.getUserByPhone(phone)
                _user.value = response
                onResult(response)
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error fetching user by phone: ${e.localizedMessage}", e)
                _user.value = null
                onResult(null)
            } finally {
                _isLoading.value = false
            }
        }
    }
    fun login(emailOrPhone: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val request = LoginRequest(identifier = emailOrPhone, password = password)
                val response = RetrofitClient.authApi.login(request)

                TokenManager.saveToken(getApplication(), response.token)

                _user.value = User(
                    id = response.userId,
                    name = response.name,
                    nickname = null,
                    email = response.email,
                    phone = response.phone,
                    passwordHash = "",
                    createdAt = "",
                    updatedAt = ""
                )

                onResult(true)
            } catch (e: Exception) {
                Log.e("UserViewModel", "Login failed: ${e.localizedMessage}", e)
                _error.value = "Login failed: ${e.localizedMessage}"
                _user.value = null
                onResult(false)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadUserFromToken() {
        viewModelScope.launch {
            try {
                val token = TokenManager.getToken(getApplication()).firstOrNull()
                if (token != null) {
                    val userId = "00000000-0000-0000-0000-000000009999"
                    val response = RetrofitClient.userApi.getUserById(userId)
                    _user.value = response
                }
            } catch (e: Exception) {
                Log.e("UserViewModel", "Failed to load user from token", e)
            }
        }
    }
}