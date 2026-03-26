package com.lunalunera.investigation.evidence.dto;

public record EvidenceCreateDTO(
    String description,
    String location,
    String type,
    Long caseId
) {
}
