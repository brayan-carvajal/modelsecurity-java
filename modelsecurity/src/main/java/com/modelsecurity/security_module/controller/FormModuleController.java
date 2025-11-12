package com.modelsecurity.security_module.controller;

import com.modelsecurity.security_module.dto.FormModuleDto;
import com.modelsecurity.security_module.entity.Form;
import com.modelsecurity.security_module.entity.FormModule;
import com.modelsecurity.security_module.entity.Module;
import com.modelsecurity.security_module.mapper.FormModuleMapper;
import com.modelsecurity.security_module.service.interfaces.IBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/form-modules")
@RequiredArgsConstructor
public class FormModuleController {

    private final IBaseService<FormModule> formModuleService;
    private final IBaseService<Form> formService;
    private final IBaseService<Module> moduleService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<FormModuleDto>> getAll() {
        List<FormModuleDto> list = formModuleService.findAll()
                .stream()
                .map(FormModuleMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<FormModuleDto> getById(@PathVariable Integer id) {
        return formModuleService.findById(id)
                .map(fm -> ResponseEntity.ok(FormModuleMapper.toDto(fm)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FormModuleDto> create(@RequestBody FormModuleDto dto) {
        var formOpt = formService.findById(dto.getFormId());
        var moduleOpt = moduleService.findById(dto.getModuleId());

        if (formOpt.isEmpty() || moduleOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        FormModule entity = FormModuleMapper.toEntity(dto);
        entity.setForm(formOpt.get());
        entity.setModule(moduleOpt.get());

        FormModule saved = formModuleService.save(entity);
        return ResponseEntity.ok(FormModuleMapper.toDto(saved));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            formModuleService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
