package com.lunalunera.investigation.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetectiveResponseDTO {
    private Long id;
    private String name;
    private String badgeNumber;
    private String specialization;
}
