package com.lunalunera.investigation.cases.dto;

import com.lunalunera.investigation.cases.model.CaseStatus;

public record CaseCreateDTO(
    String title,
    String description,
    CaseStatus status,
    Long detectiveId
) {
}
