package com.lunalunera.investigation.cases.repository;

import com.lunalunera.investigation.cases.model.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseRepository extends JpaRepository<Case, Long> {
}
