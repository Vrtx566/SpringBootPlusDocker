package com.lunalunera.investigation.service;

import com.lunalunera.investigation.model.Detective;
import com.lunalunera.investigation.repository.DetectiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetectiveService {
    private final DetectiveRepository detectiveRepository;

    public List<Detective> findAll() { return detectiveRepository.findAll(); }
    public Optional<Detective> findById(Long id) { return detectiveRepository.findById(id); }
    public Detective save(Detective d) { return detectiveRepository.save(d); }
    public void deleteById(Long id) { detectiveRepository.deleteById(id); }
}
