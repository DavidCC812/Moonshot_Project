package com.example.moonshot.user;

import com.example.moonshot.user.dto.UserRequest;
import com.example.moonshot.user.dto.UserResponse;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public UserResponse getUserById(UUID id) {
        return userRepository.findById(id)
                .map(this::mapToResponseDto)
                .orElse(null);
    }

    public UserResponse getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(this::mapToResponseDto)
                .orElse(null);
    }

    public UserResponse getUserByPhone(String phone) {
        return userRepository.findByPhone(phone)
                .map(this::mapToResponseDto)
                .orElse(null);
    }

    @Transactional
    public UserResponse createUser(UserRequest dto) {
        User user = User.builder()
                .name(dto.getName())
                .nickname(dto.getNickname())
                .email(dto.getEmail())
                .passwordHash(passwordEncoder.encode(dto.getPasswordHash()))
                .phone(dto.getPhone())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        User saved = userRepository.save(user);
        return mapToResponseDto(saved);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    private UserResponse mapToResponseDto(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .phone(user.getPhone())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
