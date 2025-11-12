package com.modelsecurity.security_module.controller;

import com.modelsecurity.security_module.dto.RolDto;
import com.modelsecurity.security_module.entity.Rol;
import com.modelsecurity.security_module.mapper.RolMapper;
import com.modelsecurity.security_module.service.interfaces.IBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RolController {

    private final IBaseService<Rol> rolService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RolDto>> getAll() {
        List<RolDto> list = rolService.findAll()
                .stream()
                .map(RolMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RolDto> getById(@PathVariable Integer id) {
        return rolService.findById(id)
                .map(r -> ResponseEntity.ok(RolMapper.toDto(r)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RolDto> create(@RequestBody RolDto dto) {
        Rol entity = RolMapper.toEntity(dto);
        Rol saved = rolService.save(entity);
        return ResponseEntity.ok(RolMapper.toDto(saved));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RolDto> update(@PathVariable Integer id, @RequestBody RolDto dto) {
        return rolService.findById(id)
                .map(existing -> {
                    existing.setName(dto.getName());
                    existing.setDescription(dto.getDescription());
                    Rol updated = rolService.update(id, existing);
                    return ResponseEntity.ok(RolMapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            rolService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
