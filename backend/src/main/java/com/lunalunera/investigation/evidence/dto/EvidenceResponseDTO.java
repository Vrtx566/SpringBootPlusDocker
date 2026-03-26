package com.lunalunera.investigation.evidence.dto;

public record EvidenceResponseDTO(
    Long id,
    String description,
    String location,
    String type,
    Long caseId
) {
}
