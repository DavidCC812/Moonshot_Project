package com.example.moonshot.userselecteddestination;

import com.example.moonshot.userselecteddestination.dto.UserSelectedDestinationRequest;
import com.example.moonshot.userselecteddestination.dto.UserSelectedDestinationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-selected-destinations")
public class UserSelectedDestinationController {

    private final UserSelectedDestinationService service;

    public UserSelectedDestinationController(UserSelectedDestinationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserSelectedDestinationResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSelectedDestinationResponse> getById(@PathVariable UUID id) {
        UserSelectedDestinationResponse response = service.getById(id);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UserSelectedDestinationResponse> create(@RequestBody UserSelectedDestinationRequest request) {
        UserSelectedDestinationResponse created = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
