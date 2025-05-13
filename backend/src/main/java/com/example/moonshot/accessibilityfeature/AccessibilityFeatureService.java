package com.example.moonshot.accessibilityfeature;

import com.example.moonshot.accessibilityfeature.dto.AccessibilityFeatureRequest;
import com.example.moonshot.accessibilityfeature.dto.AccessibilityFeatureResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccessibilityFeatureService {

    private final AccessibilityFeatureRepository featureRepository;

    public AccessibilityFeatureService(AccessibilityFeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    public List<AccessibilityFeatureResponse> getAllFeatures() {
        return featureRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public AccessibilityFeatureResponse getFeatureById(UUID id) {
        return featureRepository.findById(id)
                .map(this::mapToResponse)
                .orElse(null);
    }

    @Transactional
    public AccessibilityFeatureResponse createFeature(AccessibilityFeatureRequest dto) {
        AccessibilityFeature feature = AccessibilityFeature.builder()
                .name(dto.getName())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        AccessibilityFeature saved = featureRepository.save(feature);
        return mapToResponse(saved);
    }

    public void deleteFeature(UUID id) {
        featureRepository.deleteById(id);
    }

    private AccessibilityFeatureResponse mapToResponse(AccessibilityFeature feature) {
        return AccessibilityFeatureResponse.builder()
                .id(feature.getId())
                .name(feature.getName())
                .createdAt(feature.getCreatedAt())
                .updatedAt(feature.getUpdatedAt())
                .build();
    }
}
