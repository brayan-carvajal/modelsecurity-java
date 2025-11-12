package com.modelsecurity.cinema_module.controller;

import com.modelsecurity.cinema_module.dto.ShiftDto;
import com.modelsecurity.cinema_module.entity.Shift;
import com.modelsecurity.cinema_module.mapper.ShiftMapper;
import com.modelsecurity.cinema_module.service.interfaces.IShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shifts")
@RequiredArgsConstructor
public class ShiftController {

    private final IShiftService shiftService;

    // Obtener todos los turnos
    @GetMapping
    public ResponseEntity<List<ShiftDto>> getAll() {
        List<ShiftDto> list = shiftService.findAll()
                .stream()
                .map(ShiftMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    // Obtener un turno por ID
    @GetMapping("/{id}")
    public ResponseEntity<ShiftDto> getById(@PathVariable Integer id) {
        return shiftService.findById(id)
                .map(shift -> ResponseEntity.ok(ShiftMapper.toDto(shift)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear turno
    @PostMapping
    public ResponseEntity<ShiftDto> create(@Valid @RequestBody ShiftDto dto) {
        Shift entity = ShiftMapper.toEntity(dto);
        Shift saved = shiftService.save(entity);
        return ResponseEntity.ok(ShiftMapper.toDto(saved));
    }

    // Actualizar turno
    @PutMapping("/{id}")
    public ResponseEntity<ShiftDto> update(@PathVariable Integer id, @Valid @RequestBody ShiftDto dto) {
        return shiftService.findById(id)
                .map(existing -> {
                    existing.setStartTime(dto.getStartTime());
                    existing.setEndTime(dto.getEndTime());
                    existing.setShiftDate(dto.getShiftDate());
                    existing.setIsDeleted(dto.getIsDeleted());

                    Shift updated = shiftService.update(id, existing);
                    return ResponseEntity.ok(ShiftMapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar turno
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            shiftService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}