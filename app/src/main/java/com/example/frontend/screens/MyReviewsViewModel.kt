package com.example.frontend.screens

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf

class MyReviewsViewModel : ViewModel() {
    val myReviews = mutableStateListOf<Review>()

    fun addReview(review: Review) {
        myReviews.add(0, review)
    }
}