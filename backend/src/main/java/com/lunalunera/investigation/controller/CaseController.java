package com.lunalunera.investigation.controller;

import com.lunalunera.investigation.dto.CaseCreateDTO;
import com.lunalunera.investigation.dto.CaseResponseDTO;
import com.lunalunera.investigation.dto.CaseUpdateDTO;
import com.lunalunera.investigation.service.CaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cases")
@RequiredArgsConstructor
public class CaseController {
    private final CaseService caseService;

    @GetMapping
    public List<CaseResponseDTO> getAll() {
        return caseService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaseResponseDTO> getById(@PathVariable Long id) {
        return caseService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CaseResponseDTO> create(@RequestBody CaseCreateDTO dto) {
        CaseResponseDTO created = caseService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CaseResponseDTO> update(@PathVariable Long id, @RequestBody CaseUpdateDTO dto) {
        CaseResponseDTO updated = caseService.update(id, dto);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (caseService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        caseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
