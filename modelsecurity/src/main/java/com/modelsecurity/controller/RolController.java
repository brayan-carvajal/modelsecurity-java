package com.modelsecurity.controller;

import com.modelsecurity.dto.RolDto;
import com.modelsecurity.entity.Rol;
import com.modelsecurity.mapper.RolMapper;
import com.modelsecurity.service.interfaces.IBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RolController {

    private final IBaseService<Rol> rolService;

    @GetMapping
    public ResponseEntity<List<RolDto>> getAll() {
        List<RolDto> list = rolService.findAll()
                .stream()
                .map(RolMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolDto> getById(@PathVariable Integer id) {
        return rolService.findById(id)
                .map(r -> ResponseEntity.ok(RolMapper.toDto(r)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RolDto> create(@RequestBody RolDto dto) {
        Rol entity = RolMapper.toEntity(dto);
        Rol saved = rolService.save(entity);
        return ResponseEntity.ok(RolMapper.toDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolDto> update(@PathVariable Integer id, @RequestBody RolDto dto) {
        return rolService.findById(id)
                .map(existing -> {
                    existing.setName(dto.getName());
                    existing.setDescription(dto.getDescription());
                    existing.setIsDeleted(dto.getIsDeleted());
                    Rol updated = rolService.update(id, existing);
                    return ResponseEntity.ok(RolMapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            rolService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
