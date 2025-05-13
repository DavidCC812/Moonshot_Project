package com.example.moonshot.itineraryaccessibility;

import com.example.moonshot.accessibilityfeature.AccessibilityFeature;
import com.example.moonshot.accessibilityfeature.AccessibilityFeatureRepository;
import com.example.moonshot.itinerary.Itinerary;
import com.example.moonshot.itinerary.ItineraryRepository;
import com.example.moonshot.itineraryaccessibility.dto.ItineraryAccessibilityRequest;
import com.example.moonshot.itineraryaccessibility.dto.ItineraryAccessibilityResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ItineraryAccessibilityService {

    private final ItineraryAccessibilityRepository repository;
    private final ItineraryRepository itineraryRepository;
    private final AccessibilityFeatureRepository featureRepository;

    public ItineraryAccessibilityService(
            ItineraryAccessibilityRepository repository,
            ItineraryRepository itineraryRepository,
            AccessibilityFeatureRepository featureRepository) {
        this.repository = repository;
        this.itineraryRepository = itineraryRepository;
        this.featureRepository = featureRepository;
    }

    public List<ItineraryAccessibilityResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ItineraryAccessibilityResponse getById(UUID id) {
        return repository.findById(id)
                .map(this::mapToResponse)
                .orElse(null);
    }

    @Transactional
    public ItineraryAccessibilityResponse create(ItineraryAccessibilityRequest request) {
        Itinerary itinerary = itineraryRepository.findById(request.getItineraryId())
                .orElseThrow(() -> new IllegalArgumentException("Itinerary not found"));

        AccessibilityFeature feature = featureRepository.findById(request.getFeatureId())
                .orElseThrow(() -> new IllegalArgumentException("Accessibility feature not found"));

        ItineraryAccessibility entity = ItineraryAccessibility.builder()
                .itinerary(itinerary)
                .feature(feature)
                .build();

        ItineraryAccessibility saved = repository.save(entity);
        return mapToResponse(saved);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    private ItineraryAccessibilityResponse mapToResponse(ItineraryAccessibility entity) {
        return ItineraryAccessibilityResponse.builder()
                .id(entity.getId())
                .itineraryId(entity.getItinerary().getId())
                .featureId(entity.getFeature().getId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
