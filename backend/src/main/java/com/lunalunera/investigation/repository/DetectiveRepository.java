package com.lunalunera.investigation.repository;

import com.lunalunera.investigation.model.Detective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetectiveRepository extends JpaRepository<Detective, Long> {
}
