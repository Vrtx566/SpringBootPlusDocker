package com.lunalunera.investigation.dto;

import com.lunalunera.investigation.model.SuspectStatus;

public record SuspectResponseDTO(
    Long id,
    String name,
    String description,
    SuspectStatus status
) {
}

