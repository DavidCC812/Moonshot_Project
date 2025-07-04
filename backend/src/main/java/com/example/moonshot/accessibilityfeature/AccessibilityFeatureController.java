package com.example.moonshot.accessibilityfeature;

import com.example.moonshot.accessibilityfeature.dto.AccessibilityFeatureRequest;
import com.example.moonshot.accessibilityfeature.dto.AccessibilityFeatureResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/accessibility-features")
public class AccessibilityFeatureController {

    private final AccessibilityFeatureService featureService;

    public AccessibilityFeatureController(AccessibilityFeatureService featureService) {
        this.featureService = featureService;
    }

    @GetMapping
    public List<AccessibilityFeatureResponse> getAllFeatures() {
        return featureService.getAllFeatures();
    }


    @GetMapping("/{id}")
    public AccessibilityFeatureResponse getFeatureById(@PathVariable UUID id) {
        AccessibilityFeature feature = featureService.getFeatureById(id);
        return AccessibilityFeatureResponse.from(feature);
    }

    // Create and persist a new feature based on the request
    @PostMapping
    public AccessibilityFeatureResponse createFeature(@RequestBody AccessibilityFeatureRequest request) {
        AccessibilityFeature created = featureService.createFeature(request);
        return AccessibilityFeatureResponse.from(created);
    }

    @DeleteMapping("/{id}")
    public void deleteFeature(@PathVariable UUID id) {
        featureService.deleteFeature(id);
    }
}