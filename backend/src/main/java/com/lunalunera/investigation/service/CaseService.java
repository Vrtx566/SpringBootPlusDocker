package com.lunalunera.investigation.service;

import com.lunalunera.investigation.model.Case;
import com.lunalunera.investigation.repository.CaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CaseService {
    private final CaseRepository caseRepository;

    public List<Case> findAll() { return caseRepository.findAll(); }
    public Optional<Case> findById(Long id) { return caseRepository.findById(id); }
    public Case save(Case c) { return caseRepository.save(c); }
    public void deleteById(Long id) { caseRepository.deleteById(id); }
}
