package com.lunalunera.investigation.suspect.dto;

import com.lunalunera.investigation.suspect.model.SuspectStatus;

public record SuspectUpdateDTO(
    String name,
    String description,
    SuspectStatus status
) {
}
