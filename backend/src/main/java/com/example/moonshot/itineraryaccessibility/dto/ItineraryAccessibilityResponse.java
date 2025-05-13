package com.example.moonshot.itineraryaccessibility.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ItineraryAccessibilityResponse {
    private UUID id;
    private UUID itineraryId;
    private UUID featureId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
