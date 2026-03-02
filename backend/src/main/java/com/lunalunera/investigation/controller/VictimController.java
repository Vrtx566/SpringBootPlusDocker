package com.lunalunera.investigation.controller;

import com.lunalunera.investigation.model.Victim;
import com.lunalunera.investigation.service.VictimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/victims")
@RequiredArgsConstructor
public class VictimController {
    private final VictimService victimService;

    @GetMapping
    public List<Victim> getAll() { return victimService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Victim> getById(@PathVariable Long id) {
        return victimService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Victim create(@RequestBody Victim v) { return victimService.save(v); }

    @PutMapping("/{id}")
    public ResponseEntity<Victim> update(@PathVariable Long id, @RequestBody Victim v) {
        return victimService.findById(id).map(existing -> {
            v.setId(id);
            return ResponseEntity.ok(victimService.save(v));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (victimService.findById(id).isEmpty()) return ResponseEntity.notFound().build();
        victimService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
