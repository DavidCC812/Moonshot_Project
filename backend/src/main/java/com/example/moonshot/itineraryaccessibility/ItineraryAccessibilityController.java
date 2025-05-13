package com.example.moonshot.itineraryaccessibility;

import com.example.moonshot.itineraryaccessibility.dto.ItineraryAccessibilityRequest;
import com.example.moonshot.itineraryaccessibility.dto.ItineraryAccessibilityResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/itinerary-accessibility")
public class ItineraryAccessibilityController {

    private final ItineraryAccessibilityService service;

    public ItineraryAccessibilityController(ItineraryAccessibilityService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ItineraryAccessibilityResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItineraryAccessibilityResponse> getById(@PathVariable UUID id) {
        ItineraryAccessibilityResponse response = service.getById(id);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ItineraryAccessibilityResponse> create(@RequestBody ItineraryAccessibilityRequest request) {
        ItineraryAccessibilityResponse created = service.create(request);
        return ResponseEntity.status(201).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
