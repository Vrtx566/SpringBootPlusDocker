package com.lunalunera.investigation.suspect.repository;

import com.lunalunera.investigation.suspect.model.Suspect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuspectRepository extends JpaRepository<Suspect, Long> {
}
