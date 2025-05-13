package com.example.moonshot.itinerarystep;

import com.example.moonshot.itinerary.Itinerary;
import com.example.moonshot.itinerary.ItineraryRepository;
import com.example.moonshot.itinerarystep.dto.ItineraryStepRequest;
import com.example.moonshot.itinerarystep.dto.ItineraryStepResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ItineraryStepService {

    private final ItineraryStepRepository itineraryStepRepository;
    private final ItineraryRepository itineraryRepository;

    public ItineraryStepService(ItineraryStepRepository itineraryStepRepository,
                                ItineraryRepository itineraryRepository) {
        this.itineraryStepRepository = itineraryStepRepository;
        this.itineraryRepository = itineraryRepository;
    }

    public List<ItineraryStepResponse> getAll() {
        return itineraryStepRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ItineraryStepResponse getById(UUID id) {
        return itineraryStepRepository.findById(id)
                .map(this::mapToResponse)
                .orElse(null);
    }

    public List<ItineraryStepResponse> getByItineraryId(UUID itineraryId) {
        return itineraryStepRepository.findByItinerary_IdOrderByStepIndexAsc(itineraryId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }


    @Transactional
    public ItineraryStepResponse create(ItineraryStepRequest request) {
        Itinerary itinerary = itineraryRepository.findById(request.getItineraryId())
                .orElseThrow(() -> new IllegalArgumentException("Itinerary not found"));

        ItineraryStep step = ItineraryStep.builder()
                .itinerary(itinerary)
                .stepIndex(request.getStepIndex())
                .title(request.getTitle())
                .description(request.getDescription())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        ItineraryStep saved = itineraryStepRepository.save(step);
        return mapToResponse(saved);
    }

    public void delete(UUID id) {
        itineraryStepRepository.deleteById(id);
    }

    private ItineraryStepResponse mapToResponse(ItineraryStep step) {
        return ItineraryStepResponse.builder()
                .id(step.getId())
                .itineraryId(step.getItinerary().getId())
                .stepIndex(step.getStepIndex())
                .title(step.getTitle())
                .description(step.getDescription())
                .latitude(step.getLatitude())
                .longitude(step.getLongitude())
                .createdAt(step.getCreatedAt())
                .updatedAt(step.getUpdatedAt())
                .build();
    }
}
