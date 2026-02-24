package com.lunalunera.investigation.controller;

import com.lunalunera.investigation.model.Evidence;
import com.lunalunera.investigation.service.EvidenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/evidence")
@RequiredArgsConstructor
public class EvidenceController {
    private final EvidenceService evidenceService;

    @GetMapping
    public List<Evidence> getAll() { return evidenceService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Evidence> getById(@PathVariable Long id) {
        return evidenceService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Evidence create(@RequestBody Evidence e) { return evidenceService.save(e); }

    @PutMapping("/{id}")
    public ResponseEntity<Evidence> update(@PathVariable Long id, @RequestBody Evidence e) {
        return evidenceService.findById(id).map(existing -> {
            e.setId(id);
            return ResponseEntity.ok(evidenceService.save(e));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (evidenceService.findById(id).isEmpty()) return ResponseEntity.notFound().build();
        evidenceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
