package com.modelsecurity.controller;

import com.modelsecurity.dto.FormDto;
import com.modelsecurity.entity.Form;
import com.modelsecurity.mapper.FormMapper;
import com.modelsecurity.service.interfaces.IBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/forms")
@RequiredArgsConstructor
public class FormController {

    private final IBaseService<Form> formService;

    @GetMapping
    public ResponseEntity<List<FormDto>> getAll() {
        List<FormDto> list = formService.findAll()
                .stream()
                .map(FormMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormDto> getById(@PathVariable Integer id) {
        return formService.findById(id)
                .map(f -> ResponseEntity.ok(FormMapper.toDto(f)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FormDto> create(@RequestBody FormDto dto) {
        Form entity = FormMapper.toEntity(dto);
        Form saved = formService.save(entity);
        return ResponseEntity.ok(FormMapper.toDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormDto> update(@PathVariable Integer id, @RequestBody FormDto dto) {
        return formService.findById(id)
                .map(existing -> {
                    existing.setName(dto.getName());
                    existing.setDescription(dto.getDescription());
                    existing.setIsDeleted(dto.getIsDeleted());
                    Form updated = formService.update(id, existing);
                    return ResponseEntity.ok(FormMapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            formService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
