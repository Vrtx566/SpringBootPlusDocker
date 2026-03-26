package com.lunalunera.investigation.victim.dto;

public record VictimCreateDTO(
    String name,
    String location,
    String discoveryDescription,
    Long caseId
) {
}
