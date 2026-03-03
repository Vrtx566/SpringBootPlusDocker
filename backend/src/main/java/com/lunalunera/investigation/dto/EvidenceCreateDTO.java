package com.lunalunera.investigation.dto;

public record EvidenceCreateDTO(
    String description,
    String location,
    String type,
    Long caseId
) {
}

