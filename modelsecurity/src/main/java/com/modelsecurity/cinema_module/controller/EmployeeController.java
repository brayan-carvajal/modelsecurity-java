package com.modelsecurity.cinema_module.controller;

import com.modelsecurity.cinema_module.dto.EmployeeDto;
import com.modelsecurity.cinema_module.entity.Employee;
import com.modelsecurity.cinema_module.mapper.EmployeeMapper;
import com.modelsecurity.cinema_module.service.interfaces.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final IEmployeeService employeeService;

    // Obtener todos los empleados
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAll() {
        List<EmployeeDto> list = employeeService.findAll()
                .stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    // Obtener un empleado por ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable Integer id) {
        return employeeService.findById(id)
                .map(employee -> ResponseEntity.ok(EmployeeMapper.toDto(employee)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear empleado
    @PostMapping
    public ResponseEntity<EmployeeDto> create(@Valid @RequestBody EmployeeDto dto) {
        Employee entity = EmployeeMapper.toEntity(dto);
        Employee saved = employeeService.save(entity);
        return ResponseEntity.ok(EmployeeMapper.toDto(saved));
    }

    // Actualizar empleado
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> update(@PathVariable Integer id, @Valid @RequestBody EmployeeDto dto) {
        return employeeService.findById(id)
                .map(existing -> {
                    existing.setFirstName(dto.getFirstName());
                    existing.setLastName(dto.getLastName());
                    existing.setPosition(dto.getPosition());
                    existing.setHireDate(dto.getHireDate());
                    existing.setIsDeleted(dto.getIsDeleted());

                    Employee updated = employeeService.update(id, existing);
                    return ResponseEntity.ok(EmployeeMapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar empleado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            employeeService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}