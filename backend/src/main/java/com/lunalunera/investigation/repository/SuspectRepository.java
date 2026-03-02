package com.lunalunera.investigation.repository;

import com.lunalunera.investigation.model.Suspect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuspectRepository extends JpaRepository<Suspect, Long> {
}
