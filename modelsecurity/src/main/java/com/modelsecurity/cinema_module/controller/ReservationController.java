package com.modelsecurity.cinema_module.controller;

import com.modelsecurity.cinema_module.dto.ReservationDto;
import com.modelsecurity.cinema_module.entity.Reservation;
import com.modelsecurity.cinema_module.mapper.ReservationMapper;
import com.modelsecurity.cinema_module.service.interfaces.IReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final IReservationService reservationService;

    // Obtener todas las reservas
    @GetMapping
    public ResponseEntity<List<ReservationDto>> getAll() {
        List<ReservationDto> list = reservationService.findAll()
                .stream()
                .map(ReservationMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    // Obtener una reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getById(@PathVariable Integer id) {
        return reservationService.findById(id)
                .map(reservation -> ResponseEntity.ok(ReservationMapper.toDto(reservation)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear reserva
    @PostMapping
    public ResponseEntity<ReservationDto> create(@Valid @RequestBody ReservationDto dto) {
        Reservation entity = ReservationMapper.toEntity(dto);
        Reservation saved = reservationService.save(entity);
        return ResponseEntity.ok(ReservationMapper.toDto(saved));
    }

    // Actualizar reserva
    @PutMapping("/{id}")
    public ResponseEntity<ReservationDto> update(@PathVariable Integer id, @Valid @RequestBody ReservationDto dto) {
        return reservationService.findById(id)
                .map(existing -> {
                    existing.setReservationDate(dto.getReservationDate());
                    existing.setStatus(dto.getStatus());
                    existing.setIsDeleted(dto.getIsDeleted());

                    Reservation updated = reservationService.update(id, existing);
                    return ResponseEntity.ok(ReservationMapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            reservationService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}