package com.lunalunera.investigation.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetectiveUpdateDTO {
    private String name;
    private String badgeNumber;
    private String specialization;
}
