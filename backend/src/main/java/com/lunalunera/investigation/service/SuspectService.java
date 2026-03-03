package com.lunalunera.investigation.service;

import com.lunalunera.investigation.dto.SuspectCreateDTO;
import com.lunalunera.investigation.dto.SuspectResponseDTO;
import com.lunalunera.investigation.dto.SuspectUpdateDTO;
import com.lunalunera.investigation.model.Suspect;
import com.lunalunera.investigation.repository.SuspectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SuspectService {
    private final SuspectRepository suspectRepository;

    public List<SuspectResponseDTO> findAll() {
        return suspectRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<SuspectResponseDTO> findById(Long id) {
        return suspectRepository.findById(id).map(this::convertToResponseDTO);
    }

    public SuspectResponseDTO create(SuspectCreateDTO dto) {
        Suspect suspect = new Suspect();
        suspect.setName(dto.name());
        suspect.setDescription(dto.description());
        suspect.setStatus(dto.status());

        Suspect saved = suspectRepository.save(suspect);
        return convertToResponseDTO(saved);
    }

    public SuspectResponseDTO update(Long id, SuspectUpdateDTO dto) {
        Optional<Suspect> existingSuspect = suspectRepository.findById(id);
        if (existingSuspect.isPresent()) {
            Suspect suspect = existingSuspect.get();
            suspect.setName(dto.name());
            suspect.setDescription(dto.description());
            suspect.setStatus(dto.status());

            Suspect updated = suspectRepository.save(suspect);
            return convertToResponseDTO(updated);
        }
        return null;
    }

    public void deleteById(Long id) {
        suspectRepository.deleteById(id);
    }

    private SuspectResponseDTO convertToResponseDTO(Suspect suspect) {
        return new SuspectResponseDTO(
                suspect.getId(),
                suspect.getName(),
                suspect.getDescription(),
                suspect.getStatus()
        );
    }
}
