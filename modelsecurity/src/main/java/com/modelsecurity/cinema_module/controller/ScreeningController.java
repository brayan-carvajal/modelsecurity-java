package com.modelsecurity.cinema_module.controller;

import com.modelsecurity.cinema_module.dto.ScreeningDto;
import com.modelsecurity.cinema_module.entity.Screening;
import com.modelsecurity.cinema_module.mapper.ScreeningMapper;
import com.modelsecurity.cinema_module.service.interfaces.IScreeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/screenings")
@RequiredArgsConstructor
public class ScreeningController {

    private final IScreeningService screeningService;

    // Obtener todas las funciones
    @GetMapping
    public ResponseEntity<List<ScreeningDto>> getAll() {
        List<ScreeningDto> list = screeningService.findAll()
                .stream()
                .map(ScreeningMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    // Obtener una función por ID
    @GetMapping("/{id}")
    public ResponseEntity<ScreeningDto> getById(@PathVariable Integer id) {
        return screeningService.findById(id)
                .map(screening -> ResponseEntity.ok(ScreeningMapper.toDto(screening)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear función
    @PostMapping
    public ResponseEntity<ScreeningDto> create(@Valid @RequestBody ScreeningDto dto) {
        Screening entity = ScreeningMapper.toEntity(dto);
        Screening saved = screeningService.save(entity);
        return ResponseEntity.ok(ScreeningMapper.toDto(saved));
    }

    // Actualizar función
    @PutMapping("/{id}")
    public ResponseEntity<ScreeningDto> update(@PathVariable Integer id, @Valid @RequestBody ScreeningDto dto) {
        return screeningService.findById(id)
                .map(existing -> {
                    existing.setStartTime(dto.getStartTime());
                    existing.setEndTime(dto.getEndTime());
                    existing.setIsDeleted(dto.getIsDeleted());

                    Screening updated = screeningService.update(id, existing);
                    return ResponseEntity.ok(ScreeningMapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar función
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            screeningService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}