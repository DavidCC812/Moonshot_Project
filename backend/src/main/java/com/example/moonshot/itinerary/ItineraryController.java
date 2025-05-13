package com.example.moonshot.itinerary;

import com.example.moonshot.itinerary.dto.ItineraryRequest;
import com.example.moonshot.itinerary.dto.ItineraryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/itineraries")
public class ItineraryController {

    private final ItineraryService itineraryService;

    public ItineraryController(ItineraryService itineraryService) {
        this.itineraryService = itineraryService;
    }

    @GetMapping
    public ResponseEntity<List<ItineraryResponse>> getAll() {
        return ResponseEntity.ok(itineraryService.getAllItineraries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItineraryResponse> getById(@PathVariable UUID id) {
        ItineraryResponse itinerary = itineraryService.getItineraryById(id);
        if (itinerary == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(itinerary);
    }

    @PostMapping
    public ResponseEntity<ItineraryResponse> create(@RequestBody ItineraryRequest request) {
        ItineraryResponse created = itineraryService.createItinerary(request);
        return ResponseEntity.status(201).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        itineraryService.deleteItinerary(id);
        return ResponseEntity.noContent().build();
    }
}
