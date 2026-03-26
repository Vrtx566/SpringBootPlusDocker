package com.lunalunera.investigation.suspect.dto;

import com.lunalunera.investigation.suspect.model.SuspectStatus;

public record SuspectResponseDTO(
    Long id,
    String name,
    String description,
    SuspectStatus status
) {
}
