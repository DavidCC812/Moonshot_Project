package com.example.frontend.viewmodels

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import com.example.frontend.models.Review
import java.util.UUID

class MyReviewsViewModel : ViewModel() {
    val myReviews = mutableStateListOf<Review>()

    init {
         // Mock data for demonstration purposes
        val now = java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", java.util.Locale.US)
            .format(java.util.Date())
        val userId = UUID.fromString("00000000-0000-0000-0000-000000000000")
        val itineraryIds = listOf(
            UUID.fromString("11111111-1111-1111-1111-111111111111"),
            UUID.fromString("55555555-5555-5555-5555-555555555555"),
            UUID.fromString("33333333-3333-3333-3333-333333333333")
        )
        // Preloaded sample reviews
        myReviews.addAll(
            listOf(
                Review(
                    id = UUID.randomUUID(),
                    userId = userId,
                    itineraryId = itineraryIds[0],
                    rating = 5f,
                    comment = "Amazing experience!",
                    createdAt = now,
                    updatedAt = null
                ),
                Review(
                    id = UUID.randomUUID(),
                    userId = userId,
                    itineraryId = itineraryIds[1],
                    rating = 4f,
                    comment = "Great itinerary, but could use more accessibility details.",
                    createdAt = now,
                    updatedAt = null
                ),
                Review(
                    id = UUID.randomUUID(),
                    userId = userId,
                    itineraryId = itineraryIds[2],
                    rating = 3f,
                    comment = "Good, but some places were not as accessible as advertised.",
                    createdAt = now,
                    updatedAt = null
                )
            )
        )
    }
        // Adds a new review to the top of the list
        fun addReview(review: Review) {
        myReviews.add(0, review)
    }
}