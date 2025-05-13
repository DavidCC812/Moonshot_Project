package com.example.moonshot.usercountryaccess;

import com.example.moonshot.country.Country;
import com.example.moonshot.country.CountryRepository;
import com.example.moonshot.usercountryaccess.dto.UserCountryAccessRequest;
import com.example.moonshot.usercountryaccess.dto.UserCountryAccessResponse;
import com.example.moonshot.user.User;
import com.example.moonshot.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserCountryAccessService {

    private final UserCountryAccessRepository userCountryAccessRepository;
    private final UserRepository userRepository;
    private final CountryRepository countryRepository;

    public UserCountryAccessService(
            UserCountryAccessRepository userCountryAccessRepository,
            UserRepository userRepository,
            CountryRepository countryRepository) {
        this.userCountryAccessRepository = userCountryAccessRepository;
        this.userRepository = userRepository;
        this.countryRepository = countryRepository;
    }

    public List<UserCountryAccessResponse> getAllAccesses() {
        return userCountryAccessRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserCountryAccessResponse createAccess(UserCountryAccessRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + request.getUserId()));

        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new IllegalArgumentException("Country not found: " + request.getCountryId()));

        UserCountryAccess access = UserCountryAccess.builder()
                .user(user)
                .country(country)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return mapToResponse(userCountryAccessRepository.save(access));
    }

    public void deleteAccess(UUID id) {
        userCountryAccessRepository.deleteById(id);
    }

    private UserCountryAccessResponse mapToResponse(UserCountryAccess access) {
        return UserCountryAccessResponse.builder()
                .id(access.getId())
                .userId(access.getUser().getId())
                .countryId(access.getCountry().getId())
                .countryName(access.getCountry().getName())
                .createdAt(access.getCreatedAt())
                .updatedAt(access.getUpdatedAt())
                .build();
    }
}
