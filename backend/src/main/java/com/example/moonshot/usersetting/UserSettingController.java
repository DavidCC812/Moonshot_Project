package com.example.moonshot.usersetting;

import com.example.moonshot.usersetting.dto.UserSettingRequest;
import com.example.moonshot.usersetting.dto.UserSettingResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-settings")
public class UserSettingController {

    private final UserSettingService userSettingService;

    public UserSettingController(UserSettingService userSettingService) {
        this.userSettingService = userSettingService;
    }

    @GetMapping
    public ResponseEntity<List<UserSettingResponse>> getAllUserSettings() {
        return ResponseEntity.ok(userSettingService.getAllUserSettings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSettingResponse> getUserSettingById(@PathVariable UUID id) {
        UserSettingResponse setting = userSettingService.getUserSettingById(id);
        if (setting == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(setting);
    }

    @PostMapping
    public ResponseEntity<UserSettingResponse> createUserSetting(@RequestBody UserSettingRequest request) {
        UserSettingResponse created = userSettingService.createUserSetting(request);
        return ResponseEntity.status(201).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserSetting(@PathVariable UUID id) {
        userSettingService.deleteUserSetting(id);
        return ResponseEntity.noContent().build();
    }
}
