package com.example.moonshot.setting;

import com.example.moonshot.setting.dto.SettingRequest;
import com.example.moonshot.setting.dto.SettingResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/settings")
public class SettingController {

    private final SettingService settingService;

    public SettingController(SettingService settingService) {
        this.settingService = settingService;
    }

    @GetMapping
    public ResponseEntity<List<SettingResponse>> getAllSettings() {
        return ResponseEntity.ok(settingService.getAllSettings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SettingResponse> getSettingById(@PathVariable UUID id) {
        SettingResponse setting = settingService.getSettingById(id);
        if (setting == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(setting);
    }

    @PostMapping
    public ResponseEntity<SettingResponse> createSetting(@RequestBody SettingRequest request) {
        SettingResponse created = settingService.createSetting(request);
        return ResponseEntity.status(201).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSetting(@PathVariable UUID id) {
        settingService.deleteSetting(id);
        return ResponseEntity.noContent().build();
    }
}
