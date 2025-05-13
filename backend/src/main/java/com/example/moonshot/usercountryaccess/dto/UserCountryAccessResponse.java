package com.example.moonshot.usercountryaccess.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class UserCountryAccessResponse {
    private UUID id;
    private UUID userId;
    private UUID countryId;
    private String countryName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
