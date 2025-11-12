package com.modelsecurity.cinema_module.controller;

import com.modelsecurity.cinema_module.dto.ScreeningEmployeeDto;
import com.modelsecurity.cinema_module.entity.ScreeningEmployee;
import com.modelsecurity.cinema_module.mapper.ScreeningEmployeeMapper;
import com.modelsecurity.cinema_module.service.interfaces.IScreeningEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/screening-employees")
@RequiredArgsConstructor
public class ScreeningEmployeeController {

    private final IScreeningEmployeeService screeningEmployeeService;

    // Obtener todas las asignaciones función-empleado
    @GetMapping
    public ResponseEntity<List<ScreeningEmployeeDto>> getAll() {
        List<ScreeningEmployeeDto> list = screeningEmployeeService.findAll()
                .stream()
                .map(ScreeningEmployeeMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    // Obtener una asignación por ID
    @GetMapping("/{id}")
    public ResponseEntity<ScreeningEmployeeDto> getById(@PathVariable Integer id) {
        return screeningEmployeeService.findById(id)
                .map(screeningEmployee -> ResponseEntity.ok(ScreeningEmployeeMapper.toDto(screeningEmployee)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear asignación
    @PostMapping
    public ResponseEntity<ScreeningEmployeeDto> create(@Valid @RequestBody ScreeningEmployeeDto dto) {
        ScreeningEmployee entity = ScreeningEmployeeMapper.toEntity(dto);
        ScreeningEmployee saved = screeningEmployeeService.save(entity);
        return ResponseEntity.ok(ScreeningEmployeeMapper.toDto(saved));
    }

    // Actualizar asignación
    @PutMapping("/{id}")
    public ResponseEntity<ScreeningEmployeeDto> update(@PathVariable Integer id, @Valid @RequestBody ScreeningEmployeeDto dto) {
        return screeningEmployeeService.findById(id)
                .map(existing -> {
                    existing.setIsDeleted(dto.getIsDeleted());

                    ScreeningEmployee updated = screeningEmployeeService.update(id, existing);
                    return ResponseEntity.ok(ScreeningEmployeeMapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar asignación
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            screeningEmployeeService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}