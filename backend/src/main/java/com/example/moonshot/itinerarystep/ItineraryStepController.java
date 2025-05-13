package com.example.moonshot.itinerarystep;

import com.example.moonshot.itinerarystep.dto.ItineraryStepRequest;
import com.example.moonshot.itinerarystep.dto.ItineraryStepResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/itinerary-steps")
public class ItineraryStepController {

    private final ItineraryStepService itineraryStepService;

    public ItineraryStepController(ItineraryStepService itineraryStepService) {
        this.itineraryStepService = itineraryStepService;
    }

    @GetMapping
    public ResponseEntity<List<ItineraryStepResponse>> getAll() {
        return ResponseEntity.ok(itineraryStepService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItineraryStepResponse> getById(@PathVariable UUID id) {
        ItineraryStepResponse step = itineraryStepService.getById(id);
        if (step == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(step);
    }

    @PostMapping
    public ResponseEntity<ItineraryStepResponse> create(@RequestBody ItineraryStepRequest request) {
        ItineraryStepResponse created = itineraryStepService.create(request);
        return ResponseEntity.status(201).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        itineraryStepService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-itinerary/{itineraryId}")
    public ResponseEntity<List<ItineraryStepResponse>> getByItineraryId(@PathVariable UUID itineraryId) {
        List<ItineraryStepResponse> steps = itineraryStepService.getByItineraryId(itineraryId);
        return ResponseEntity.ok(steps);
    }

}
