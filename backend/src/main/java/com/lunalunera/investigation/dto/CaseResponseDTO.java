package com.lunalunera.investigation.dto;

import com.lunalunera.investigation.model.CaseStatus;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CaseResponseDTO {
    private Long id;
    private String title;
    private String description;
    private CaseStatus status;
    private LocalDateTime createdAt;
    private DetectiveResponseDTO detective;
}

