package com.example.moonshot.userselecteddestination.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class UserSelectedDestinationResponse {
    private UUID id;
    private UUID userId;
    private UUID destinationId;
    private String destinationName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
