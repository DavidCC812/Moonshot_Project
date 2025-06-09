package com.example.frontend.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontend.models.Destination
import com.example.frontend.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.util.Log

class DestinationViewModel : ViewModel() {

    private val _destinations = MutableStateFlow<List<Destination>>(emptyList())
    val destinations: StateFlow<List<Destination>> = _destinations

    init {
        fetchDestinations()
    }

    private fun fetchDestinations() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.destinationApi.getAllDestinations()
                _destinations.value = response
                Log.d("DestinationViewModel", "Fetched ${response.size} destinations")
            } catch (e: Exception) {
                Log.e("DestinationViewModel", "Failed to fetch destinations", e)
            }
        }
    }
}
