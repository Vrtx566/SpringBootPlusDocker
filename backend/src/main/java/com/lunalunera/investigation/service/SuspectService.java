package com.lunalunera.investigation.service;

import com.lunalunera.investigation.model.Suspect;
import com.lunalunera.investigation.repository.SuspectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SuspectService {
    private final SuspectRepository suspectRepository;

    public List<Suspect> findAll() { return suspectRepository.findAll(); }
    public Optional<Suspect> findById(Long id) { return suspectRepository.findById(id); }
    public Suspect save(Suspect s) { return suspectRepository.save(s); }
    public void deleteById(Long id) { suspectRepository.deleteById(id); }
}
