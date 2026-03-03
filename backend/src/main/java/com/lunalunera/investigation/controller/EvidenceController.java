package com.lunalunera.investigation.controller;

import com.lunalunera.investigation.dto.EvidenceCreateDTO;
import com.lunalunera.investigation.dto.EvidenceResponseDTO;
import com.lunalunera.investigation.dto.EvidenceUpdateDTO;
import com.lunalunera.investigation.service.EvidenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/evidence")
@RequiredArgsConstructor
public class EvidenceController {
    private final EvidenceService evidenceService;

    @GetMapping
    public List<EvidenceResponseDTO> getAll() {
        return evidenceService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvidenceResponseDTO> getById(@PathVariable Long id) {
        return evidenceService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EvidenceResponseDTO> create(@RequestBody EvidenceCreateDTO dto) {
        EvidenceResponseDTO created = evidenceService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvidenceResponseDTO> update(@PathVariable Long id, @RequestBody EvidenceUpdateDTO dto) {
        EvidenceResponseDTO updated = evidenceService.update(id, dto);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (evidenceService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        evidenceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
