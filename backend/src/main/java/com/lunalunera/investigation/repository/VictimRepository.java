package com.lunalunera.investigation.repository;

import com.lunalunera.investigation.model.Victim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VictimRepository extends JpaRepository<Victim, Long> {
}
