package com.lunalunera.investigation.victim.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lunalunera.investigation.cases.model.Case;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "victims")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Victim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;

    @Column(columnDefinition = "TEXT")
    private String discoveryDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "case_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "victims", "evidence", "detective"})
    private Case caseEntity;
}
