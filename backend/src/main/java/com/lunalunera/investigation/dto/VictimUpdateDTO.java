package com.lunalunera.investigation.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VictimUpdateDTO {
    private String name;
    private String location;
    private String discoveryDescription;
    private Long caseId;
}

