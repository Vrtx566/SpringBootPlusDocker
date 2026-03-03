package com.lunalunera.investigation.dto;

import com.lunalunera.investigation.model.CaseStatus;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CaseUpdateDTO {
    private String title;
    private String description;
    private CaseStatus status;
    private Long detectiveId;
}

