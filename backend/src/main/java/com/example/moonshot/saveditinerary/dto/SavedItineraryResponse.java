package com.example.moonshot.saveditinerary.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class SavedItineraryResponse {
    private UUID id;
    private UUID userId;
    private UUID itineraryId;
    private LocalDateTime savedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
