package com.lunalunera.investigation.service;

import com.lunalunera.investigation.model.Evidence;
import com.lunalunera.investigation.repository.EvidenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EvidenceService {
    private final EvidenceRepository evidenceRepository;

    public List<Evidence> findAll() { return evidenceRepository.findAll(); }
    public Optional<Evidence> findById(Long id) { return evidenceRepository.findById(id); }
    public Evidence save(Evidence e) { return evidenceRepository.save(e); }
    public void deleteById(Long id) { evidenceRepository.deleteById(id); }
}
