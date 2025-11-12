package com.modelsecurity.cinema_module.controller;

import com.modelsecurity.cinema_module.dto.TheaterRoomDto;
import com.modelsecurity.cinema_module.entity.TheaterRoom;
import com.modelsecurity.cinema_module.mapper.TheaterRoomMapper;
import com.modelsecurity.cinema_module.service.interfaces.ITheaterRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/theater-rooms")
@RequiredArgsConstructor
public class TheaterRoomController {

    private final ITheaterRoomService theaterRoomService;

    // Obtener todas las salas
    @GetMapping
    public ResponseEntity<List<TheaterRoomDto>> getAll() {
        List<TheaterRoomDto> list = theaterRoomService.findAll()
                .stream()
                .map(TheaterRoomMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    // Obtener una sala por ID
    @GetMapping("/{id}")
    public ResponseEntity<TheaterRoomDto> getById(@PathVariable Integer id) {
        return theaterRoomService.findById(id)
                .map(theaterRoom -> ResponseEntity.ok(TheaterRoomMapper.toDto(theaterRoom)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear sala
    @PostMapping
    public ResponseEntity<TheaterRoomDto> create(@Valid @RequestBody TheaterRoomDto dto) {
        TheaterRoom entity = TheaterRoomMapper.toEntity(dto);
        TheaterRoom saved = theaterRoomService.save(entity);
        return ResponseEntity.ok(TheaterRoomMapper.toDto(saved));
    }

    // Actualizar sala
    @PutMapping("/{id}")
    public ResponseEntity<TheaterRoomDto> update(@PathVariable Integer id, @Valid @RequestBody TheaterRoomDto dto) {
        return theaterRoomService.findById(id)
                .map(existing -> {
                    existing.setName(dto.getName());
                    existing.setCapacity(dto.getCapacity());
                    existing.setIsDeleted(dto.getIsDeleted());

                    TheaterRoom updated = theaterRoomService.update(id, existing);
                    return ResponseEntity.ok(TheaterRoomMapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar sala
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            theaterRoomService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}