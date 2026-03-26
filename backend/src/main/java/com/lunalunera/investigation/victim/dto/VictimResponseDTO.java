package com.lunalunera.investigation.victim.dto;

public record VictimResponseDTO(
    Long id,
    String name,
    String location,
    String discoveryDescription,
    Long caseId
) {
}
