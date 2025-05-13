package com.example.moonshot.review;

import com.example.moonshot.itinerary.Itinerary;
import com.example.moonshot.itinerary.ItineraryRepository;
import com.example.moonshot.review.dto.ReviewRequest;
import com.example.moonshot.review.dto.ReviewResponse;
import com.example.moonshot.user.User;
import com.example.moonshot.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ItineraryRepository itineraryRepository;

    public ReviewService(ReviewRepository reviewRepository,
                         UserRepository userRepository,
                         ItineraryRepository itineraryRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.itineraryRepository = itineraryRepository;
    }

    public List<ReviewResponse> getAllReviews() {
        return reviewRepository.findAllWithRelations()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ReviewResponse getReviewById(UUID id) {
        return reviewRepository.findById(id)
                .map(this::mapToResponse)
                .orElse(null);
    }

    @Transactional
    public ReviewResponse createReview(ReviewRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Itinerary itinerary = itineraryRepository.findById(request.getItineraryId())
                .orElseThrow(() -> new IllegalArgumentException("Itinerary not found"));

        Review review = Review.builder()
                .user(user)
                .itinerary(itinerary)
                .rating(request.getRating())
                .comment(request.getComment())
                .build();

        Review saved = reviewRepository.save(review);
        return mapToResponse(saved);
    }

    public void deleteReview(UUID id) {
        reviewRepository.deleteById(id);
    }

    private ReviewResponse mapToResponse(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .userId(review.getUser().getId())
                .itineraryId(review.getItinerary().getId())
                .rating(review.getRating())
                .comment(review.getComment())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
    }
}
