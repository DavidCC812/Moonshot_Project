package com.example.frontend.screens

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf

class SavedItinerariesViewModel : ViewModel() {
    val savedItineraries = mutableStateListOf<String>()

    fun saveItinerary(itinerary: String) {
        if (!savedItineraries.contains(itinerary)) {
            savedItineraries.add(itinerary)
        }
    }

    fun removeItinerary(itinerary: String) {
        savedItineraries.remove(itinerary)
    }
}