package com.example.moonshot.country;

import com.example.moonshot.country.dto.CountryRequest;
import com.example.moonshot.country.dto.CountryResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<CountryResponse> getAllCountries() {
        return countryRepository.findAll().stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public CountryResponse getCountryById(UUID id) {
        return countryRepository.findById(id)
                .map(this::mapToResponseDto)
                .orElse(null);
    }

    @Transactional
    public CountryResponse createCountry(CountryRequest request) {
        Country country = Country.builder()
                .name(request.getName())
                .available(request.isAvailable())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return mapToResponseDto(countryRepository.save(country));
    }

    public void deleteCountry(UUID id) {
        countryRepository.deleteById(id);
    }

    private CountryResponse mapToResponseDto(Country country) {
        return CountryResponse.builder()
                .id(country.getId())
                .name(country.getName())
                .available(country.isAvailable())
                .createdAt(country.getCreatedAt())
                .updatedAt(country.getUpdatedAt())
                .build();
    }
}
