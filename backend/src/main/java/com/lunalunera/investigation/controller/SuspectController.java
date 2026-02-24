package com.lunalunera.investigation.controller;

import com.lunalunera.investigation.model.Suspect;
import com.lunalunera.investigation.service.SuspectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/suspects")
@RequiredArgsConstructor
public class SuspectController {
    private final SuspectService suspectService;

    @GetMapping
    public List<Suspect> getAll() { return suspectService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Suspect> getById(@PathVariable Long id) {
        return suspectService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Suspect create(@RequestBody Suspect s) { return suspectService.save(s); }

    @PutMapping("/{id}")
    public ResponseEntity<Suspect> update(@PathVariable Long id, @RequestBody Suspect s) {
        return suspectService.findById(id).map(existing -> {
            s.setId(id);
            return ResponseEntity.ok(suspectService.save(s));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (suspectService.findById(id).isEmpty()) return ResponseEntity.notFound().build();
        suspectService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
