package com.modelsecurity.cinema_module.controller;

import com.modelsecurity.cinema_module.dto.EmployeeShiftDto;
import com.modelsecurity.cinema_module.entity.EmployeeShift;
import com.modelsecurity.cinema_module.mapper.EmployeeShiftMapper;
import com.modelsecurity.cinema_module.service.interfaces.IEmployeeShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employee-shifts")
@RequiredArgsConstructor
public class EmployeeShiftController {

    private final IEmployeeShiftService employeeShiftService;

    // Obtener todas las asignaciones empleado-turno
    @GetMapping
    public ResponseEntity<List<EmployeeShiftDto>> getAll() {
        List<EmployeeShiftDto> list = employeeShiftService.findAll()
                .stream()
                .map(EmployeeShiftMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    // Obtener una asignación por ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeShiftDto> getById(@PathVariable Integer id) {
        return employeeShiftService.findById(id)
                .map(employeeShift -> ResponseEntity.ok(EmployeeShiftMapper.toDto(employeeShift)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear asignación
    @PostMapping
    public ResponseEntity<EmployeeShiftDto> create(@Valid @RequestBody EmployeeShiftDto dto) {
        EmployeeShift entity = EmployeeShiftMapper.toEntity(dto);
        EmployeeShift saved = employeeShiftService.save(entity);
        return ResponseEntity.ok(EmployeeShiftMapper.toDto(saved));
    }

    // Actualizar asignación
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeShiftDto> update(@PathVariable Integer id, @Valid @RequestBody EmployeeShiftDto dto) {
        return employeeShiftService.findById(id)
                .map(existing -> {
                    existing.setIsDeleted(dto.getIsDeleted());

                    EmployeeShift updated = employeeShiftService.update(id, existing);
                    return ResponseEntity.ok(EmployeeShiftMapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar asignación
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            employeeShiftService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}