package com.lunalunera.investigation.controller;

import com.lunalunera.investigation.model.Case;
import com.lunalunera.investigation.service.CaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cases")
@RequiredArgsConstructor
public class CaseController {
    private final CaseService caseService;

    @GetMapping
    public List<Case> getAll() { return caseService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Case> getById(@PathVariable Long id) {
        return caseService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Case create(@RequestBody Case c) { return caseService.save(c); }

    @PutMapping("/{id}")
    public ResponseEntity<Case> update(@PathVariable Long id, @RequestBody Case c) {
        return caseService.findById(id).map(existing -> {
            c.setId(id);
            return ResponseEntity.ok(caseService.save(c));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (caseService.findById(id).isEmpty()) return ResponseEntity.notFound().build();
        caseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
