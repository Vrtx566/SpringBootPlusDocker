package com.lunalunera.investigation.controller;

import com.lunalunera.investigation.dto.VictimCreateDTO;
import com.lunalunera.investigation.dto.VictimResponseDTO;
import com.lunalunera.investigation.dto.VictimUpdateDTO;
import com.lunalunera.investigation.service.VictimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/victims")
@RequiredArgsConstructor
public class VictimController {
    private final VictimService victimService;

    @GetMapping
    public List<VictimResponseDTO> getAll() {
        return victimService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VictimResponseDTO> getById(@PathVariable Long id) {
        return victimService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<VictimResponseDTO> create(@RequestBody VictimCreateDTO dto) {
        VictimResponseDTO created = victimService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VictimResponseDTO> update(@PathVariable Long id, @RequestBody VictimUpdateDTO dto) {
        VictimResponseDTO updated = victimService.update(id, dto);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (victimService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        victimService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
