package com.lunalunera.investigation.dto;

public record VictimUpdateDTO(
    String name,
    String location,
    String discoveryDescription,
    Long caseId
) {
}

