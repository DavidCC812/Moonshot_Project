package com.example.moonshot.usercountryaccess;

import com.example.moonshot.usercountryaccess.dto.UserCountryAccessRequest;
import com.example.moonshot.usercountryaccess.dto.UserCountryAccessResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-country-access")
public class UserCountryAccessController {

    private final UserCountryAccessService service;

    public UserCountryAccessController(UserCountryAccessService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserCountryAccessResponse> getAllAccesses() {
        return service.getAllAccesses();
    }

    @PostMapping
    public UserCountryAccessResponse createAccess(@RequestBody UserCountryAccessRequest request) {
        UserCountryAccess created = service.createAccess(request);
        return UserCountryAccessResponse.from(created);
    }

    @DeleteMapping("/{id}")
    public void deleteAccess(@PathVariable UUID id) {
        service.deleteAccess(id);
    }
}
