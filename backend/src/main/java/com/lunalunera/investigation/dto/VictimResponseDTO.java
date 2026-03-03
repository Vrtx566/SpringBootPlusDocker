package com.lunalunera.investigation.dto;

public record VictimResponseDTO(
    Long id,
    String name,
    String location,
    String discoveryDescription,
    Long caseId
) {
}

