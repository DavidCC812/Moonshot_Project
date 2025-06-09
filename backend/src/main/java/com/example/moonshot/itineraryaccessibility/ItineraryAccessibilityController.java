package com.example.moonshot.itineraryaccessibility;

import com.example.moonshot.itineraryaccessibility.dto.ItineraryAccessibilityRequest;
import com.example.moonshot.itineraryaccessibility.dto.ItineraryAccessibilityResponse;
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
    public List<ItineraryAccessibilityResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ItineraryAccessibilityResponse getById(@PathVariable UUID id) {
        ItineraryAccessibility entity = service.getById(id);
        return ItineraryAccessibilityResponse.from(entity);
    }

    @PostMapping
    public ItineraryAccessibilityResponse create(@RequestBody ItineraryAccessibilityRequest request) {
        ItineraryAccessibility created = service.create(request);
        return ItineraryAccessibilityResponse.from(created);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
