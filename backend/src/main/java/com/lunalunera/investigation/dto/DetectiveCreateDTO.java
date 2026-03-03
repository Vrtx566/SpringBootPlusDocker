package com.lunalunera.investigation.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetectiveCreateDTO {
    private String name;
    private String badgeNumber;
    private String specialization;
}

