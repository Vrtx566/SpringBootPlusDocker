package com.lunalunera.investigation.dto;

import lombok.*;
import com.lunalunera.investigation.model.SuspectStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuspectCreateDTO {
    private String name;
    private String description;
    private SuspectStatus status;
}

