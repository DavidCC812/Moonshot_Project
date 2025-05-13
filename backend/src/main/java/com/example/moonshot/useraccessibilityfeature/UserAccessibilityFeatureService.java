package com.example.moonshot.useraccessibilityfeature;

import com.example.moonshot.accessibilityfeature.AccessibilityFeature;
import com.example.moonshot.accessibilityfeature.AccessibilityFeatureRepository;
import com.example.moonshot.user.User;
import com.example.moonshot.user.UserRepository;
import com.example.moonshot.useraccessibilityfeature.dto.UserAccessibilityFeatureRequest;
import com.example.moonshot.useraccessibilityfeature.dto.UserAccessibilityFeatureResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserAccessibilityFeatureService {

    private final UserAccessibilityFeatureRepository repository;
    private final UserRepository userRepository;
    private final AccessibilityFeatureRepository featureRepository;

    public UserAccessibilityFeatureService(
            UserAccessibilityFeatureRepository repository,
            UserRepository userRepository,
            AccessibilityFeatureRepository featureRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.featureRepository = featureRepository;
    }

    public List<UserAccessibilityFeatureResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public UserAccessibilityFeatureResponse getById(UUID id) {
        return repository.findById(id)
                .map(this::mapToResponse)
                .orElse(null);
    }

    @Transactional
    public UserAccessibilityFeatureResponse create(UserAccessibilityFeatureRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        AccessibilityFeature feature = featureRepository.findById(request.getFeatureId())
                .orElseThrow(() -> new IllegalArgumentException("Accessibility feature not found"));

        UserAccessibilityFeature link = UserAccessibilityFeature.builder()
                .user(user)
                .feature(feature)
                .build();

        UserAccessibilityFeature saved = repository.save(link);
        return mapToResponse(saved);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    private UserAccessibilityFeatureResponse mapToResponse(UserAccessibilityFeature link) {
        return UserAccessibilityFeatureResponse.builder()
                .id(link.getId())
                .userId(link.getUser().getId())
                .featureId(link.getFeature().getId())
                .createdAt(link.getCreatedAt())
                .updatedAt(link.getUpdatedAt())
                .build();
    }
}
