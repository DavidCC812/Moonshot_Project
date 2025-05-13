package com.example.moonshot.usersetting.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class UserSettingResponse {
    private UUID id;
    private UUID userId;
    private UUID settingId;
    private boolean value;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
