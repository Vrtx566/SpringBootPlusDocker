package com.lunalunera.investigation.controller;

import com.lunalunera.investigation.dto.SuspectCreateDTO;
import com.lunalunera.investigation.dto.SuspectResponseDTO;
import com.lunalunera.investigation.dto.SuspectUpdateDTO;
import com.lunalunera.investigation.service.SuspectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/suspects")
@RequiredArgsConstructor
public class SuspectController {
    private final SuspectService suspectService;

    @GetMapping
    public List<SuspectResponseDTO> getAll() {
        return suspectService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuspectResponseDTO> getById(@PathVariable Long id) {
        return suspectService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SuspectResponseDTO> create(@RequestBody SuspectCreateDTO dto) {
        SuspectResponseDTO created = suspectService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuspectResponseDTO> update(@PathVariable Long id, @RequestBody SuspectUpdateDTO dto) {
        SuspectResponseDTO updated = suspectService.update(id, dto);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (suspectService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        suspectService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
