package com.lunalunera.investigation.cases.dto;

import com.lunalunera.investigation.cases.model.CaseStatus;
import com.lunalunera.investigation.detective.dto.DetectiveResponseDTO;
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
