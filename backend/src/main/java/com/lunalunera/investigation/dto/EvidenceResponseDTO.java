package com.lunalunera.investigation.dto;

public record EvidenceResponseDTO(
    Long id,
    String description,
    String location,
    String type,
    Long caseId
) {
}

