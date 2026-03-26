package com.lunalunera.investigation.victim.repository;

import com.lunalunera.investigation.victim.model.Victim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VictimRepository extends JpaRepository<Victim, Long> {
}
