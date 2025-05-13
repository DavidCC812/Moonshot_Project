package com.example.moonshot.userselecteddestination;

import com.example.moonshot.destination.Destination;
import com.example.moonshot.destination.DestinationRepository;
import com.example.moonshot.user.User;
import com.example.moonshot.user.UserRepository;
import com.example.moonshot.userselecteddestination.dto.UserSelectedDestinationRequest;
import com.example.moonshot.userselecteddestination.dto.UserSelectedDestinationResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserSelectedDestinationService {

    private final UserSelectedDestinationRepository repository;
    private final UserRepository userRepository;
    private final DestinationRepository destinationRepository;

    public UserSelectedDestinationService(
            UserSelectedDestinationRepository repository,
            UserRepository userRepository,
            DestinationRepository destinationRepository
    ) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.destinationRepository = destinationRepository;
    }

    public List<UserSelectedDestinationResponse> getAll() {
        return repository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public UserSelectedDestinationResponse getById(UUID id) {
        return repository.findById(id)
                .map(this::mapToResponse)
                .orElse(null);
    }

    @Transactional
    public UserSelectedDestinationResponse create(UserSelectedDestinationRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + request.getUserId()));

        Destination destination = destinationRepository.findById(request.getDestinationId())
                .orElseThrow(() -> new IllegalArgumentException("Destination not found: " + request.getDestinationId()));

        UserSelectedDestination link = UserSelectedDestination.builder()
                .user(user)
                .destination(destination)
                .build();

        return mapToResponse(repository.save(link));
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    private UserSelectedDestinationResponse mapToResponse(UserSelectedDestination entity) {
        return UserSelectedDestinationResponse.builder()
                .id(entity.getId())
                .userId(entity.getUser().getId())
                .destinationId(entity.getDestination().getId())
                .destinationName(entity.getDestination().getName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
