package com.example.moonshot.useraccessibilityfeature;

import com.example.moonshot.useraccessibilityfeature.dto.UserAccessibilityFeatureRequest;
import com.example.moonshot.useraccessibilityfeature.dto.UserAccessibilityFeatureResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-accessibility-features")
public class UserAccessibilityFeatureController {

    private final UserAccessibilityFeatureService service;

    public UserAccessibilityFeatureController(UserAccessibilityFeatureService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserAccessibilityFeatureResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAccessibilityFeatureResponse> getById(@PathVariable UUID id) {
        UserAccessibilityFeatureResponse feature = service.getById(id);
        if (feature == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(feature);
    }

    @PostMapping
    public ResponseEntity<UserAccessibilityFeatureResponse> create(@RequestBody UserAccessibilityFeatureRequest request) {
        UserAccessibilityFeatureResponse created = service.create(request);
        return ResponseEntity.status(201).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
