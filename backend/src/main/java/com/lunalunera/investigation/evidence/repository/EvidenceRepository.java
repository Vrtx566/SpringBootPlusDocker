package com.lunalunera.investigation.evidence.repository;

import com.lunalunera.investigation.evidence.model.Evidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvidenceRepository extends JpaRepository<Evidence, Long> {
}
