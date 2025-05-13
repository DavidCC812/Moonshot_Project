package com.example.moonshot.setting.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class SettingResponse {
    private UUID id;
    private String settingKey;
    private String label;
    private String description;
    private boolean defaultValue;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
