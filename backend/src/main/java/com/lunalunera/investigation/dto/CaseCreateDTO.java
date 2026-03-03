package com.lunalunera.investigation.dto;

import com.lunalunera.investigation.model.CaseStatus;

public record CaseCreateDTO(
    String title,
    String description,
    CaseStatus status,
    Long detectiveId
) {
}

