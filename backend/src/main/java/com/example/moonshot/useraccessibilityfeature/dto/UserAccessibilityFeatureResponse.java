package com.example.moonshot.useraccessibilityfeature.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class UserAccessibilityFeatureResponse {
    private UUID id;
    private UUID userId;
    private UUID featureId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
