package com.example.moonshot.accessibilityfeature;

import com.example.moonshot.accessibilityfeature.dto.AccessibilityFeatureRequest;
import com.example.moonshot.accessibilityfeature.dto.AccessibilityFeatureResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<AccessibilityFeatureResponse>> getAllFeatures() {
        return ResponseEntity.ok(featureService.getAllFeatures());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccessibilityFeatureResponse> getFeatureById(@PathVariable UUID id) {
        AccessibilityFeatureResponse feature = featureService.getFeatureById(id);
        if (feature == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(feature);
    }

    @PostMapping
    public ResponseEntity<AccessibilityFeatureResponse> createFeature(@RequestBody AccessibilityFeatureRequest request) {
        AccessibilityFeatureResponse created = featureService.createFeature(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeature(@PathVariable UUID id) {
        featureService.deleteFeature(id);
        return ResponseEntity.noContent().build();
    }
}
