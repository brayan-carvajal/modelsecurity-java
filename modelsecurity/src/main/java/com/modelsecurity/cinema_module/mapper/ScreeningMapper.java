package com.modelsecurity.cinema_module.mapper;

import com.modelsecurity.cinema_module.dto.ScreeningDto;
import com.modelsecurity.cinema_module.entity.Screening;

public class ScreeningMapper {

    // Convierte entidad → DTO
    public static ScreeningDto toDto(Screening entity) {
        if (entity == null) return null;

        ScreeningDto dto = new ScreeningDto();
        dto.setId(entity.getId());
        dto.setMovieId(entity.getMovie() != null ? entity.getMovie().getId() : null);
        dto.setTheaterRoomId(entity.getTheaterRoom() != null ? entity.getTheaterRoom().getId() : null);
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }

    // Convierte DTO → entidad
    public static Screening toEntity(ScreeningDto dto) {
        if (dto == null) return null;

        Screening entity = new Screening();
        entity.setId(dto.getId());
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
        entity.setIsDeleted(dto.getIsDeleted() != null ? dto.getIsDeleted() : false);
        return entity;
    }
}