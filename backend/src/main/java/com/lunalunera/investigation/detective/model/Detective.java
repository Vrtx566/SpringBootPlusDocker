package com.lunalunera.investigation.detective.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lunalunera.investigation.cases.model.Case;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "detectives")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Detective {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String badgeNumber;

    private String specialization;

    @JsonIgnore
    @OneToMany(mappedBy = "detective", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Case> cases;
}
