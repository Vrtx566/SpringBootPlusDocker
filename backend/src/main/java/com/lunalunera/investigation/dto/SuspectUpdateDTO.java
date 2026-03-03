package com.lunalunera.investigation.dto;

import com.lunalunera.investigation.model.SuspectStatus;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuspectUpdateDTO {
    private String name;
    private String description;
    private SuspectStatus status;
}

