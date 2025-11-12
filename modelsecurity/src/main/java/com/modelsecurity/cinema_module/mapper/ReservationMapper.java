package com.modelsecurity.cinema_module.mapper;

import com.modelsecurity.cinema_module.dto.ReservationDto;
import com.modelsecurity.cinema_module.entity.Reservation;

public class ReservationMapper {

    // Convierte entidad → DTO
    public static ReservationDto toDto(Reservation entity) {
        if (entity == null) return null;

        ReservationDto dto = new ReservationDto();
        dto.setId(entity.getId());
        dto.setCustomerId(entity.getCustomer() != null ? entity.getCustomer().getId() : null);
        dto.setScreeningId(entity.getScreening() != null ? entity.getScreening().getId() : null);
        dto.setReservationDate(entity.getReservationDate());
        dto.setStatus(entity.getStatus());
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }

    // Convierte DTO → entidad
    public static Reservation toEntity(ReservationDto dto) {
        if (dto == null) return null;

        Reservation entity = new Reservation();
        entity.setId(dto.getId());
        entity.setReservationDate(dto.getReservationDate());
        entity.setStatus(dto.getStatus());
        entity.setIsDeleted(dto.getIsDeleted() != null ? dto.getIsDeleted() : false);
        return entity;
    }
}