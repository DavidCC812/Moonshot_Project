package com.example.moonshot.userselecteddestination;

import com.example.moonshot.userselecteddestination.dto.UserSelectedDestinationRequest;
import com.example.moonshot.userselecteddestination.dto.UserSelectedDestinationResponse;
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
    public List<UserSelectedDestinationResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public UserSelectedDestinationResponse getById(@PathVariable UUID id) {
        UserSelectedDestination entity = service.getById(id);
        return UserSelectedDestinationResponse.from(entity);
    }

    @PostMapping
    public UserSelectedDestinationResponse create(@RequestBody UserSelectedDestinationRequest request) {
        UserSelectedDestination created = service.create(request);
        return UserSelectedDestinationResponse.from(created);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
