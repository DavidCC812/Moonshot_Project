package com.example.moonshot.usercountryaccess;

import com.example.moonshot.usercountryaccess.dto.UserCountryAccessRequest;
import com.example.moonshot.usercountryaccess.dto.UserCountryAccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-country-access")
public class UserCountryAccessController {

    private final UserCountryAccessService userCountryAccessService;

    public UserCountryAccessController(UserCountryAccessService userCountryAccessService) {
        this.userCountryAccessService = userCountryAccessService;
    }

    @GetMapping
    public ResponseEntity<List<UserCountryAccessResponse>> getAllAccesses() {
        return ResponseEntity.ok(userCountryAccessService.getAllAccesses());
    }

    @PostMapping
    public ResponseEntity<UserCountryAccessResponse> createAccess(@RequestBody UserCountryAccessRequest request) {
        UserCountryAccessResponse created = userCountryAccessService.createAccess(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccess(@PathVariable UUID id) {
        userCountryAccessService.deleteAccess(id);
        return ResponseEntity.noContent().build();
    }
}
