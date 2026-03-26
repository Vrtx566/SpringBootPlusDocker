package com.lunalunera.investigation.victim.dto;

public record VictimUpdateDTO(
    String name,
    String location,
    String discoveryDescription,
    Long caseId
) {
}
