package com.modelsecurity.security_module.controller;

import com.modelsecurity.security_module.dto.RolFormPermitDto;
import com.modelsecurity.security_module.entity.Form;
import com.modelsecurity.security_module.entity.Permission;
import com.modelsecurity.security_module.entity.Rol;
import com.modelsecurity.security_module.entity.RolFormPermit;
import com.modelsecurity.security_module.mapper.RolFormPermitMapper;
import com.modelsecurity.security_module.service.interfaces.IBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rol-form-permits")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class RolFormPermitController {

    private final IBaseService<RolFormPermit> rolFormPermitService;
    private final IBaseService<Rol> rolService;
    private final IBaseService<Form> formService;
    private final IBaseService<Permission> permissionService;

    @GetMapping
    public ResponseEntity<List<RolFormPermitDto>> getAll() {
        List<RolFormPermitDto> list = rolFormPermitService.findAll()
                .stream()
                .map(RolFormPermitMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolFormPermitDto> getById(@PathVariable Integer id) {
        return rolFormPermitService.findById(id)
                .map(rfp -> ResponseEntity.ok(RolFormPermitMapper.toDto(rfp)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RolFormPermitDto> create(@RequestBody RolFormPermitDto dto) {
        var rolOpt = rolService.findById(dto.getRolId());
        var formOpt = formService.findById(dto.getFormId());
        var permOpt = permissionService.findById(dto.getPermissionId());

        if (rolOpt.isEmpty() || formOpt.isEmpty() || permOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        RolFormPermit entity = RolFormPermitMapper.toEntity(dto);
        entity.setRol(rolOpt.get());
        entity.setForm(formOpt.get());
        entity.setPermission(permOpt.get());

        RolFormPermit saved = rolFormPermitService.save(entity);
        return ResponseEntity.ok(RolFormPermitMapper.toDto(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            rolFormPermitService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
