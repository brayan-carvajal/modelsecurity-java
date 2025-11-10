package com.modelsecurity.controller;

import com.modelsecurity.dto.ModuleDto;
import com.modelsecurity.entity.Module;
import com.modelsecurity.mapper.ModuleMapper;
import com.modelsecurity.service.interfaces.IBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/modules")
@RequiredArgsConstructor
public class ModuleController {

    private final IBaseService<Module> moduleService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<ModuleDto>> getAll() {
        List<ModuleDto> list = moduleService.findAll()
                .stream()
                .map(ModuleMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ModuleDto> getById(@PathVariable Integer id) {
        return moduleService.findById(id)
                .map(m -> ResponseEntity.ok(ModuleMapper.toDto(m)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ModuleDto> create(@RequestBody ModuleDto dto) {
        Module entity = ModuleMapper.toEntity(dto);
        Module saved = moduleService.save(entity);
        return ResponseEntity.ok(ModuleMapper.toDto(saved));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ModuleDto> update(@PathVariable Integer id, @RequestBody ModuleDto dto) {
        return moduleService.findById(id)
                .map(existing -> {
                    existing.setName(dto.getName());
                    existing.setDescription(dto.getDescription());
                    existing.setIsDeleted(dto.getIsDeleted());
                    Module updated = moduleService.update(id, existing);
                    return ResponseEntity.ok(ModuleMapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            moduleService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
