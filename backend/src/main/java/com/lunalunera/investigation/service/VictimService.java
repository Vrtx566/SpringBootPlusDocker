package com.lunalunera.investigation.service;

import com.lunalunera.investigation.dto.VictimCreateDTO;
import com.lunalunera.investigation.dto.VictimResponseDTO;
import com.lunalunera.investigation.dto.VictimUpdateDTO;
import com.lunalunera.investigation.model.Case;
import com.lunalunera.investigation.model.Victim;
import com.lunalunera.investigation.repository.CaseRepository;
import com.lunalunera.investigation.repository.VictimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VictimService {
    private final VictimRepository victimRepository;
    private final CaseRepository caseRepository;

    public List<VictimResponseDTO> findAll() {
        return victimRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<VictimResponseDTO> findById(Long id) {
        return victimRepository.findById(id).map(this::convertToResponseDTO);
    }

    public VictimResponseDTO create(VictimCreateDTO dto) {
        Victim victim = new Victim();
        victim.setName(dto.getName());
        victim.setLocation(dto.getLocation());
        victim.setDiscoveryDescription(dto.getDiscoveryDescription());

        if (dto.getCaseId() != null) {
            Case caseEntity = caseRepository.findById(dto.getCaseId()).orElse(null);
            victim.setCaseEntity(caseEntity);
        }

        Victim saved = victimRepository.save(victim);
        return convertToResponseDTO(saved);
    }

    public VictimResponseDTO update(Long id, VictimUpdateDTO dto) {
        Optional<Victim> existingVictim = victimRepository.findById(id);
        if (existingVictim.isPresent()) {
            Victim victim = existingVictim.get();
            victim.setName(dto.getName());
            victim.setLocation(dto.getLocation());
            victim.setDiscoveryDescription(dto.getDiscoveryDescription());

            if (dto.getCaseId() != null) {
                Case caseEntity = caseRepository.findById(dto.getCaseId()).orElse(null);
                victim.setCaseEntity(caseEntity);
            }

            Victim updated = victimRepository.save(victim);
            return convertToResponseDTO(updated);
        }
        return null;
    }

    public void deleteById(Long id) {
        victimRepository.deleteById(id);
    }

    private VictimResponseDTO convertToResponseDTO(Victim victim) {
        return VictimResponseDTO.builder()
                .id(victim.getId())
                .name(victim.getName())
                .location(victim.getLocation())
                .discoveryDescription(victim.getDiscoveryDescription())
                .caseId(victim.getCaseEntity() != null ? victim.getCaseEntity().getId() : null)
                .build();
    }
}
