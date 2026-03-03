package com.lunalunera.investigation.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvidenceUpdateDTO {
    private String description;
    private String location;
    private String type;
    private Long caseId;
}

