package com.example.moonshot.destination;

import com.example.moonshot.country.Country;
import com.example.moonshot.country.CountryRepository;
import com.example.moonshot.destination.dto.DestinationRequest;
import com.example.moonshot.destination.dto.DestinationResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DestinationService {

    private final DestinationRepository destinationRepository;
    private final CountryRepository countryRepository;

    public DestinationService(DestinationRepository destinationRepository, CountryRepository countryRepository) {
        this.destinationRepository = destinationRepository;
        this.countryRepository = countryRepository;
    }

    public List<DestinationResponse> getAllDestinations() {
        return destinationRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public List<String> getAllDestinationTypes() {
        return destinationRepository.findDistinctTypes();
    }


    public DestinationResponse getDestinationById(UUID id) {
        return destinationRepository.findById(id)
                .map(this::mapToResponseDto)
                .orElse(null);
    }

    @Transactional
    public DestinationResponse createDestination(DestinationRequest dto) {
        Country country = countryRepository.findById(dto.getCountryId())
                .orElseThrow(() -> new IllegalArgumentException("Country not found: " + dto.getCountryId()));

        Destination destination = Destination.builder()
                .name(dto.getName())
                .type(dto.getType())
                .available(dto.isAvailable())
                .country(country)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Destination saved = destinationRepository.save(destination);
        return mapToResponseDto(saved);
    }

    public void deleteDestination(UUID id) {
        destinationRepository.deleteById(id);
    }

    public List<DestinationResponse> getAvailableDestinations() {
        return destinationRepository.findAllByAvailableTrue()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    private DestinationResponse mapToResponseDto(Destination destination) {
        return DestinationResponse.builder()
                .id(destination.getId())
                .name(destination.getName())
                .type(destination.getType())
                .available(destination.isAvailable())
                .countryId(destination.getCountry().getId())
                .countryName(destination.getCountry().getName())
                .createdAt(destination.getCreatedAt())
                .updatedAt(destination.getUpdatedAt())
                .build();
    }
}
