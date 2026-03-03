package com.lunalunera.investigation.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VictimCreateDTO {
    private String name;
    private String location;
    private String discoveryDescription;
    private Long caseId;
}

