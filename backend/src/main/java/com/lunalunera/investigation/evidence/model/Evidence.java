package com.lunalunera.investigation.evidence.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lunalunera.investigation.cases.model.Case;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "evidence")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Evidence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String location;

    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "case_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "victims", "evidence", "detective"})
    private Case caseEntity;
}
