package com.example.moonshot.usersetting;

import com.example.moonshot.setting.Setting;
import com.example.moonshot.setting.SettingRepository;
import com.example.moonshot.user.User;
import com.example.moonshot.user.UserRepository;
import com.example.moonshot.usersetting.dto.UserSettingRequest;
import com.example.moonshot.usersetting.dto.UserSettingResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserSettingService {

    private final UserSettingRepository userSettingRepository;
    private final UserRepository userRepository;
    private final SettingRepository settingRepository;

    public UserSettingService(UserSettingRepository userSettingRepository,
                              UserRepository userRepository,
                              SettingRepository settingRepository) {
        this.userSettingRepository = userSettingRepository;
        this.userRepository = userRepository;
        this.settingRepository = settingRepository;
    }

    @Transactional
    public UserSettingResponse createUserSetting(UserSettingRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Setting setting = settingRepository.findById(request.getSettingId())
                .orElseThrow(() -> new IllegalArgumentException("Setting not found"));

        UserSetting userSetting = UserSetting.builder()
                .user(user)
                .setting(setting)
                .value(request.isValue())
                .build();

        UserSetting saved = userSettingRepository.save(userSetting);
        return mapToResponse(saved);
    }

    public List<UserSettingResponse> getAllUserSettings() {
        return userSettingRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public UserSettingResponse getUserSettingById(UUID id) {
        return userSettingRepository.findById(id)
                .map(this::mapToResponse)
                .orElse(null);
    }

    public void deleteUserSetting(UUID id) {
        userSettingRepository.deleteById(id);
    }

    private UserSettingResponse mapToResponse(UserSetting setting) {
        return UserSettingResponse.builder()
                .id(setting.getId())
                .userId(setting.getUser().getId())
                .settingId(setting.getSetting().getId())
                .value(setting.isValue())
                .createdAt(setting.getCreatedAt())
                .updatedAt(setting.getUpdatedAt())
                .build();
    }
}
