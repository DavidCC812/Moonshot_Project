package com.example.moonshot.setting;

import com.example.moonshot.setting.dto.SettingRequest;
import com.example.moonshot.setting.dto.SettingResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SettingService {

    private final SettingRepository settingRepository;

    public SettingService(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    public List<SettingResponse> getAllSettings() {
        return settingRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public SettingResponse getSettingById(UUID id) {
        return settingRepository.findById(id)
                .map(this::mapToResponse)
                .orElse(null);
    }

    @Transactional
    public SettingResponse createSetting(SettingRequest dto) {
        Setting setting = Setting.builder()
                .settingKey(dto.getSettingKey())
                .label(dto.getLabel())
                .description(dto.getDescription())
                .defaultValue(dto.isDefaultValue())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Setting saved = settingRepository.save(setting);
        return mapToResponse(saved);
    }

    public void deleteSetting(UUID id) {
        settingRepository.deleteById(id);
    }

    private SettingResponse mapToResponse(Setting setting) {
        return SettingResponse.builder()
                .id(setting.getId())
                .settingKey(setting.getSettingKey())
                .label(setting.getLabel())
                .description(setting.getDescription())
                .defaultValue(setting.isDefaultValue())
                .createdAt(setting.getCreatedAt())
                .updatedAt(setting.getUpdatedAt())
                .build();
    }
}
