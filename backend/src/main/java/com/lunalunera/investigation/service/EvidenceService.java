package com.lunalunera.investigation.service;

import com.lunalunera.investigation.dto.EvidenceCreateDTO;
import com.lunalunera.investigation.dto.EvidenceResponseDTO;
import com.lunalunera.investigation.dto.EvidenceUpdateDTO;
import com.lunalunera.investigation.model.Case;
import com.lunalunera.investigation.model.Evidence;
import com.lunalunera.investigation.repository.CaseRepository;
import com.lunalunera.investigation.repository.EvidenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EvidenceService {
    private final EvidenceRepository evidenceRepository;
    private final CaseRepository caseRepository;

    public List<EvidenceResponseDTO> findAll() {
        return evidenceRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<EvidenceResponseDTO> findById(Long id) {
        return evidenceRepository.findById(id).map(this::convertToResponseDTO);
    }

    public EvidenceResponseDTO create(EvidenceCreateDTO dto) {
        Evidence evidence = new Evidence();
        evidence.setDescription(dto.getDescription());
        evidence.setLocation(dto.getLocation());
        evidence.setType(dto.getType());

        if (dto.getCaseId() != null) {
            Case caseEntity = caseRepository.findById(dto.getCaseId()).orElse(null);
            evidence.setCaseEntity(caseEntity);
        }

        Evidence saved = evidenceRepository.save(evidence);
        return convertToResponseDTO(saved);
    }

    public EvidenceResponseDTO update(Long id, EvidenceUpdateDTO dto) {
        Optional<Evidence> existingEvidence = evidenceRepository.findById(id);
        if (existingEvidence.isPresent()) {
            Evidence evidence = existingEvidence.get();
            evidence.setDescription(dto.getDescription());
            evidence.setLocation(dto.getLocation());
            evidence.setType(dto.getType());

            if (dto.getCaseId() != null) {
                Case caseEntity = caseRepository.findById(dto.getCaseId()).orElse(null);
                evidence.setCaseEntity(caseEntity);
            }

            Evidence updated = evidenceRepository.save(evidence);
            return convertToResponseDTO(updated);
        }
        return null;
    }

    public void deleteById(Long id) {
        evidenceRepository.deleteById(id);
    }

    private EvidenceResponseDTO convertToResponseDTO(Evidence evidence) {
        return EvidenceResponseDTO.builder()
                .id(evidence.getId())
                .description(evidence.getDescription())
                .location(evidence.getLocation())
                .type(evidence.getType())
                .caseId(evidence.getCaseEntity() != null ? evidence.getCaseEntity().getId() : null)
                .build();
    }
}
