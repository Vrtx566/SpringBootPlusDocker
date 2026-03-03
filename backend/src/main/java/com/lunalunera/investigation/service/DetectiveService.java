package com.lunalunera.investigation.service;

import com.lunalunera.investigation.dto.DetectiveCreateDTO;
import com.lunalunera.investigation.dto.DetectiveResponseDTO;
import com.lunalunera.investigation.dto.DetectiveUpdateDTO;
import com.lunalunera.investigation.model.Detective;
import com.lunalunera.investigation.repository.DetectiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DetectiveService {
    private final DetectiveRepository detectiveRepository;

    public List<DetectiveResponseDTO> findAll() {
        return detectiveRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<DetectiveResponseDTO> findById(Long id) {
        return detectiveRepository.findById(id).map(this::convertToResponseDTO);
    }

    public DetectiveResponseDTO create(DetectiveCreateDTO dto) {
        Detective detective = new Detective();
        detective.setName(dto.name());
        detective.setBadgeNumber(dto.badgeNumber());
        detective.setSpecialization(dto.specialization());

        Detective saved = detectiveRepository.save(detective);
        return convertToResponseDTO(saved);
    }

    public DetectiveResponseDTO update(Long id, DetectiveUpdateDTO dto) {
        Optional<Detective> existingDetective = detectiveRepository.findById(id);
        if (existingDetective.isPresent()) {
            Detective detective = existingDetective.get();
            detective.setName(dto.name());
            detective.setBadgeNumber(dto.badgeNumber());
            detective.setSpecialization(dto.specialization());

            Detective updated = detectiveRepository.save(detective);
            return convertToResponseDTO(updated);
        }
        return null;
    }

    public void deleteById(Long id) {
        detectiveRepository.deleteById(id);
    }

    private DetectiveResponseDTO convertToResponseDTO(Detective detective) {
        return new DetectiveResponseDTO(
                detective.getId(),
                detective.getName(),
                detective.getBadgeNumber(),
                detective.getSpecialization()
        );
    }
}
