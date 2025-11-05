package com.modelsecurity.controller;

import com.modelsecurity.dto.PermissionDto;
import com.modelsecurity.entity.Permission;
import com.modelsecurity.mapper.PermissionMapper;
import com.modelsecurity.service.interfaces.IBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final IBaseService<Permission> permissionService;

    @GetMapping
    public ResponseEntity<List<PermissionDto>> getAll() {
        List<PermissionDto> list = permissionService.findAll()
                .stream()
                .map(PermissionMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionDto> getById(@PathVariable Integer id) {
        return permissionService.findById(id)
                .map(p -> ResponseEntity.ok(PermissionMapper.toDto(p)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PermissionDto> create(@RequestBody PermissionDto dto) {
        Permission entity = PermissionMapper.toEntity(dto);
        Permission saved = permissionService.save(entity);
        return ResponseEntity.ok(PermissionMapper.toDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermissionDto> update(@PathVariable Integer id, @RequestBody PermissionDto dto) {
        return permissionService.findById(id)
                .map(existing -> {
                    existing.setName(dto.getName());
                    existing.setDescription(dto.getDescription());
                    existing.setIsDeleted(dto.getIsDeleted());
                    Permission updated = permissionService.update(id, existing);
                    return ResponseEntity.ok(PermissionMapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            permissionService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
