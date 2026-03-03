package com.lunalunera.investigation.service;

import com.lunalunera.investigation.dto.CaseCreateDTO;
import com.lunalunera.investigation.dto.CaseResponseDTO;
import com.lunalunera.investigation.dto.CaseUpdateDTO;
import com.lunalunera.investigation.dto.DetectiveResponseDTO;
import com.lunalunera.investigation.model.Case;
import com.lunalunera.investigation.model.Detective;
import com.lunalunera.investigation.repository.CaseRepository;
import com.lunalunera.investigation.repository.DetectiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CaseService {
    private final CaseRepository caseRepository;
    private final DetectiveRepository detectiveRepository;

    public List<CaseResponseDTO> findAll() {
        return caseRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<CaseResponseDTO> findById(Long id) {
        return caseRepository.findById(id).map(this::convertToResponseDTO);
    }

    public CaseResponseDTO create(CaseCreateDTO dto) {
        Case caseEntity = new Case();
        caseEntity.setTitle(dto.getTitle());
        caseEntity.setDescription(dto.getDescription());
        caseEntity.setStatus(dto.getStatus());

        if (dto.getDetectiveId() != null) {
            Detective detective = detectiveRepository.findById(dto.getDetectiveId()).orElse(null);
            caseEntity.setDetective(detective);
        }

        Case saved = caseRepository.save(caseEntity);
        return convertToResponseDTO(saved);
    }

    public CaseResponseDTO update(Long id, CaseUpdateDTO dto) {
        Optional<Case> existingCase = caseRepository.findById(id);
        if (existingCase.isPresent()) {
            Case caseEntity = existingCase.get();
            caseEntity.setTitle(dto.getTitle());
            caseEntity.setDescription(dto.getDescription());
            caseEntity.setStatus(dto.getStatus());

            if (dto.getDetectiveId() != null) {
                Detective detective = detectiveRepository.findById(dto.getDetectiveId()).orElse(null);
                caseEntity.setDetective(detective);
            }

            Case updated = caseRepository.save(caseEntity);
            return convertToResponseDTO(updated);
        }
        return null;
    }

    public void deleteById(Long id) {
        caseRepository.deleteById(id);
    }

    private CaseResponseDTO convertToResponseDTO(Case caseEntity) {
        DetectiveResponseDTO detectiveDTO = null;
        if (caseEntity.getDetective() != null) {
            detectiveDTO = DetectiveResponseDTO.builder()
                    .id(caseEntity.getDetective().getId())
                    .name(caseEntity.getDetective().getName())
                    .badgeNumber(caseEntity.getDetective().getBadgeNumber())
                    .specialization(caseEntity.getDetective().getSpecialization())
                    .build();
        }

        return CaseResponseDTO.builder()
                .id(caseEntity.getId())
                .title(caseEntity.getTitle())
                .description(caseEntity.getDescription())
                .status(caseEntity.getStatus())
                .createdAt(caseEntity.getCreatedAt())
                .detective(detectiveDTO)
                .build();
    }
}
