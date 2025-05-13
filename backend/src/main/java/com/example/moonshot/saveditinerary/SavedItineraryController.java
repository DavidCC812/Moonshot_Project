package com.example.moonshot.saveditinerary;

import com.example.moonshot.saveditinerary.dto.SavedItineraryRequest;
import com.example.moonshot.saveditinerary.dto.SavedItineraryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/saved-itineraries")
public class SavedItineraryController {

    private final SavedItineraryService service;

    public SavedItineraryController(SavedItineraryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<SavedItineraryResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SavedItineraryResponse> getById(@PathVariable UUID id) {
        SavedItineraryResponse response = service.getById(id);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SavedItineraryResponse>> getByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(service.getByUserId(userId));
    }


    @PostMapping
    public ResponseEntity<SavedItineraryResponse> create(@RequestBody SavedItineraryRequest request) {
        SavedItineraryResponse created = service.create(request);
        return ResponseEntity.status(201).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
