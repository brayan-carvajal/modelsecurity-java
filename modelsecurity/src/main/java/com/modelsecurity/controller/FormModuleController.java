package com.modelsecurity.controller;

import com.modelsecurity.dto.FormModuleDto;
import com.modelsecurity.entity.Form;
import com.modelsecurity.entity.FormModule;
import com.modelsecurity.entity.Module;
import com.modelsecurity.mapper.FormModuleMapper;
import com.modelsecurity.service.interfaces.IBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<FormModuleDto>> getAll() {
        List<FormModuleDto> list = formModuleService.findAll()
                .stream()
                .map(FormModuleMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormModuleDto> getById(@PathVariable Integer id) {
        return formModuleService.findById(id)
                .map(fm -> ResponseEntity.ok(FormModuleMapper.toDto(fm)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
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
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            formModuleService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
