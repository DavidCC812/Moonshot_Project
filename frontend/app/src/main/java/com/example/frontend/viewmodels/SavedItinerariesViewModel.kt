package com.example.frontend.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontend.models.SavedItinerary
import com.example.frontend.models.SavedItineraryRequest
import com.example.frontend.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class SavedItinerariesViewModel : ViewModel() {
    
     // Demo user ID
    private val userId = UUID.fromString("00000000-0000-0000-0000-000000009999") // Demo user

    private val _savedItineraries = MutableStateFlow<List<SavedItinerary>>(emptyList())
    val savedItineraries: StateFlow<List<SavedItinerary>> = _savedItineraries

    private val _nextPlan = MutableStateFlow<SavedItinerary?>(null)
    val nextPlan: StateFlow<SavedItinerary?> = _nextPlan

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchSavedItineraries()
    }

    fun fetchSavedItineraries() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.savedItineraryApi.getByUserId(userId)
                _savedItineraries.value = response
                _error.value = null
            } catch (e: Exception) {
                Log.e("SavedItineraries", "Fetch error: ${e.message}")
                _error.value = "Error fetching saved itineraries"
            }
        }
    }

    fun saveItinerary(itineraryId: UUID, onSuccess: (() -> Unit)? = null) {
        viewModelScope.launch {
            try {
                Log.d("SavedItineraries", "Attempting to save itinerary $itineraryId")

                val request = SavedItineraryRequest(
                    userId = userId,
                    itineraryId = itineraryId
                )
                val created = RetrofitClient.savedItineraryApi.saveItinerary(request)

                Log.d("SavedItineraries", "Successfully saved: $created")

                _savedItineraries.value = _savedItineraries.value + created

                onSuccess?.invoke()

            } catch (e: Exception) {
                Log.e("SavedItineraries", "Save error: ${e.message}", e)
            }
        }
    }

    fun removeItinerary(itineraryId: UUID) {
        viewModelScope.launch {
            val existing = _savedItineraries.value.find { it.itineraryId == itineraryId }
            if (existing != null) {
                try {
                    RetrofitClient.savedItineraryApi.deleteItinerary(existing.id)
                    _savedItineraries.value = _savedItineraries.value.filter { it.id != existing.id }
                } catch (e: Exception) {
                    Log.e("SavedItineraries", "Delete error: ${e.message}")
                }
            }
        }
    }

    fun setAsNextPlan(itineraryId: UUID) {
        Log.d("SavedItineraries", "Looking for itineraryId $itineraryId in: ${_savedItineraries.value.map { it.itineraryId }}")
        val item = _savedItineraries.value.find { it.itineraryId == itineraryId }
        if (item != null) {
            _nextPlan.value = item
            Log.d("SavedItineraries", "Set $itineraryId as next plan")
        } else {
            Log.w("SavedItineraries", "Could not set next plan — itinerary not found")
        }
    }
}
