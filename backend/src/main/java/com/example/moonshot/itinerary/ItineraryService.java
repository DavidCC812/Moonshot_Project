package com.example.moonshot.itinerary;

import com.example.moonshot.destination.Destination;
import com.example.moonshot.destination.DestinationRepository;
import com.example.moonshot.itinerary.dto.ItineraryRequest;
import com.example.moonshot.itinerary.dto.ItineraryResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ItineraryService {

    private final ItineraryRepository itineraryRepository;
    private final DestinationRepository destinationRepository;

    public ItineraryService(ItineraryRepository itineraryRepository, DestinationRepository destinationRepository) {
        this.itineraryRepository = itineraryRepository;
        this.destinationRepository = destinationRepository;
    }

    public List<ItineraryResponse> getAllItineraries() {
        return itineraryRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public ItineraryResponse getItineraryById(UUID id) {
        return itineraryRepository.findById(id)
                .map(this::mapToResponseDto)
                .orElse(null);
    }

    @Transactional
    public ItineraryResponse createItinerary(ItineraryRequest request) {
        Destination destination = destinationRepository.findById(request.getDestinationId())
                .orElseThrow(() -> new IllegalArgumentException("Destination not found"));

        Itinerary itinerary = Itinerary.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .duration(request.getDuration())
                .rating(request.getRating())
                .destination(destination)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Itinerary saved = itineraryRepository.save(itinerary);
        return mapToResponseDto(saved);
    }

    public void deleteItinerary(UUID id) {
        itineraryRepository.deleteById(id);
    }

    private ItineraryResponse mapToResponseDto(Itinerary itinerary) {
        return ItineraryResponse.builder()
                .id(itinerary.getId())
                .title(itinerary.getTitle())
                .description(itinerary.getDescription())
                .price(itinerary.getPrice())
                .duration(itinerary.getDuration())
                .rating(itinerary.getRating())
                .destinationId(itinerary.getDestination().getId())
                .createdAt(itinerary.getCreatedAt())
                .updatedAt(itinerary.getUpdatedAt())
                .build();
    }
}
