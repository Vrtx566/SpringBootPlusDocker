package com.lunalunera.investigation.evidence.dto;

public record EvidenceUpdateDTO(
    String description,
    String location,
    String type,
    Long caseId
) {
}
