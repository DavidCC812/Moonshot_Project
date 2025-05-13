package com.example.moonshot.country.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class CountryResponse {
    private UUID id;
    private String name;
    private boolean available;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
