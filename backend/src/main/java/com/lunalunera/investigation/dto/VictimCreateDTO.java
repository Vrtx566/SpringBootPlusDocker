package com.lunalunera.investigation.dto;

public record VictimCreateDTO(
    String name,
    String location,
    String discoveryDescription,
    Long caseId
) {
}

