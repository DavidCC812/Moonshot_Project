package com.example.frontend.screens

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

class SavedItinerariesViewModel : ViewModel() {
    var savedItineraries = mutableStateListOf<Pair<String, String>>()
    var nextPlan = mutableStateOf<Pair<String, String>?>(null)
    fun saveItinerary(itinerary: Pair<String, String>) {
        if (!savedItineraries.contains(itinerary)) {
            savedItineraries.add(itinerary)
        }
    }

    fun removeItinerary(itinerary: Pair<String, String>) {
        savedItineraries.remove(itinerary)
    }

    fun setAsNextPlan(itinerary: Pair<String, String>) {
        nextPlan.value = itinerary
        removeItinerary(itinerary)
    }
}