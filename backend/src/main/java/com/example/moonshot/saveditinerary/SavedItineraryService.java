package com.example.moonshot.saveditinerary;

import com.example.moonshot.itinerary.Itinerary;
import com.example.moonshot.itinerary.ItineraryRepository;
import com.example.moonshot.saveditinerary.dto.SavedItineraryRequest;
import com.example.moonshot.saveditinerary.dto.SavedItineraryResponse;
import com.example.moonshot.user.User;
import com.example.moonshot.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SavedItineraryService {

    private final SavedItineraryRepository repository;
    private final UserRepository userRepository;
    private final ItineraryRepository itineraryRepository;

    public SavedItineraryService(
            SavedItineraryRepository repository,
            UserRepository userRepository,
            ItineraryRepository itineraryRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.itineraryRepository = itineraryRepository;
    }

    public List<SavedItineraryResponse> getAll() {
        return repository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public SavedItineraryResponse getById(UUID id) {
        return repository.findById(id)
                .map(this::mapToResponse)
                .orElse(null);
    }

    public List<SavedItineraryResponse> getByUserId(UUID userId) {
        return repository.findByUserId(userId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }


    @Transactional
    public SavedItineraryResponse create(SavedItineraryRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Itinerary itinerary = itineraryRepository.findById(request.getItineraryId())
                .orElseThrow(() -> new IllegalArgumentException("Itinerary not found"));

        SavedItinerary saved = SavedItinerary.builder()
                .user(user)
                .itinerary(itinerary)
                .build();

        return mapToResponse(repository.save(saved));
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    private SavedItineraryResponse mapToResponse(SavedItinerary entity) {
        return SavedItineraryResponse.builder()
                .id(entity.getId())
                .userId(entity.getUser().getId())
                .itineraryId(entity.getItinerary().getId())
                .savedAt(entity.getSavedAt())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
