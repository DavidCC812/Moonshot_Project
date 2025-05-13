package com.example.moonshot.review.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ReviewResponse {
    private UUID id;
    private UUID userId;
    private UUID itineraryId;
    private Float rating;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
