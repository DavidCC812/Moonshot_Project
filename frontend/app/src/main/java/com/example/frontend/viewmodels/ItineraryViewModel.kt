package com.example.frontend.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontend.models.Itinerary
import com.example.frontend.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import android.util.Log

class ItineraryViewModel : ViewModel() {

    private val _itineraries = MutableStateFlow<List<Itinerary>>(emptyList())
    val itineraries: StateFlow<List<Itinerary>> = _itineraries

    init {
        fetchItineraries()
    }
    // Returns a Flow that emits the itinerary matching the given ID
    fun getItineraryById(itineraryId: String): Flow<Itinerary?> {
        return itineraries.map { list ->
            list.find { it.id.toString() == itineraryId }
        }
    }

    fun fetchItineraries() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.itineraryApi.getAllItineraries()
                _itineraries.value = response
                Log.d("ItineraryFetch", "Fetched ${response.size} itineraries")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("ItineraryFetch", "Error fetching itineraries: ${e.message}")
            }
        }
    }
}
