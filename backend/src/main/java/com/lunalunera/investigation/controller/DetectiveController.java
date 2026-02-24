package com.lunalunera.investigation.controller;

import com.lunalunera.investigation.model.Detective;
import com.lunalunera.investigation.service.DetectiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/detectives")
@RequiredArgsConstructor
public class DetectiveController {
    private final DetectiveService detectiveService;

    @GetMapping
    public List<Detective> getAll() { return detectiveService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Detective> getById(@PathVariable Long id) {
        return detectiveService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Detective create(@RequestBody Detective d) { return detectiveService.save(d); }

    @PutMapping("/{id}")
    public ResponseEntity<Detective> update(@PathVariable Long id, @RequestBody Detective d) {
        return detectiveService.findById(id).map(existing -> {
            d.setId(id);
            return ResponseEntity.ok(detectiveService.save(d));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (detectiveService.findById(id).isEmpty()) return ResponseEntity.notFound().build();
        detectiveService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
