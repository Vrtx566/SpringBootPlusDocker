package com.lunalunera.investigation.service;

import com.lunalunera.investigation.model.Victim;
import com.lunalunera.investigation.repository.VictimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VictimService {
    private final VictimRepository victimRepository;

    public List<Victim> findAll() { return victimRepository.findAll(); }
    public Optional<Victim> findById(Long id) { return victimRepository.findById(id); }
    public Victim save(Victim v) { return victimRepository.save(v); }
    public void deleteById(Long id) { victimRepository.deleteById(id); }
}
