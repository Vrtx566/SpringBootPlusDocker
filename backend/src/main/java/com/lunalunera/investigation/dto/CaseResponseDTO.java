package com.lunalunera.investigation.dto;

import com.lunalunera.investigation.model.CaseStatus;
import java.time.LocalDateTime;

public record CaseResponseDTO(
    Long id,
    String title,
    String description,
    CaseStatus status,
    LocalDateTime createdAt,
    DetectiveResponseDTO detective
) {
}

