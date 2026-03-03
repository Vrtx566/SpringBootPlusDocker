package com.lunalunera.investigation.dto;

import com.lunalunera.investigation.model.SuspectStatus;

public record SuspectUpdateDTO(
    String name,
    String description,
    SuspectStatus status
) {
}

