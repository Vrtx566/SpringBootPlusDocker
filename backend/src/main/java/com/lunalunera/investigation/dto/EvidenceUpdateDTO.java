package com.lunalunera.investigation.dto;

public record EvidenceUpdateDTO(
    String description,
    String location,
    String type,
    Long caseId
) {
}

