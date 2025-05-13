package com.example.moonshot.destination.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class DestinationResponse {
    private UUID id;
    private String name;
    private String type;
    private boolean available;
    private UUID countryId;
    private String countryName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
