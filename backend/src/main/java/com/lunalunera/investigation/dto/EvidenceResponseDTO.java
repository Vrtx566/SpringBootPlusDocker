package com.lunalunera.investigation.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvidenceResponseDTO {
    private Long id;
    private String description;
    private String location;
    private String type;
    private Long caseId;
}

