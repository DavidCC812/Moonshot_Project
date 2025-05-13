package com.example.moonshot.destination;

import com.example.moonshot.destination.dto.DestinationRequest;
import com.example.moonshot.destination.dto.DestinationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {

    private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping
    public ResponseEntity<List<DestinationResponse>> getAllDestinations() {
        return ResponseEntity.ok(destinationService.getAllDestinations());
    }

    @GetMapping("/available")
    public ResponseEntity<List<DestinationResponse>> getAvailableDestinations() {
        return ResponseEntity.ok(destinationService.getAvailableDestinations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinationResponse> getDestinationById(@PathVariable UUID id) {
        DestinationResponse destination = destinationService.getDestinationById(id);
        if (destination == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(destination);
    }

    @GetMapping("/types")
    public ResponseEntity<List<String>> getDestinationTypes() {
        return ResponseEntity.ok(destinationService.getAllDestinationTypes());
    }


    @PostMapping
    public ResponseEntity<DestinationResponse> createDestination(@RequestBody DestinationRequest request) {
        DestinationResponse created = destinationService.createDestination(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDestination(@PathVariable UUID id) {
        destinationService.deleteDestination(id);
        return ResponseEntity.noContent().build();
    }
}
