package com.lunalunera.investigation.controller;

import com.lunalunera.investigation.dto.DetectiveCreateDTO;
import com.lunalunera.investigation.dto.DetectiveResponseDTO;
import com.lunalunera.investigation.dto.DetectiveUpdateDTO;
import com.lunalunera.investigation.service.DetectiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/detectives")
@RequiredArgsConstructor
public class DetectiveController {
    private final DetectiveService detectiveService;

    @GetMapping
    public List<DetectiveResponseDTO> getAll() {
        return detectiveService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetectiveResponseDTO> getById(@PathVariable Long id) {
        return detectiveService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DetectiveResponseDTO> create(@RequestBody DetectiveCreateDTO dto) {
        DetectiveResponseDTO created = detectiveService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetectiveResponseDTO> update(@PathVariable Long id, @RequestBody DetectiveUpdateDTO dto) {
        DetectiveResponseDTO updated = detectiveService.update(id, dto);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (detectiveService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        detectiveService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
