package com.modelsecurity.cinema_module.mapper;

import com.modelsecurity.cinema_module.dto.ShiftDto;
import com.modelsecurity.cinema_module.entity.Shift;

public class ShiftMapper {

    // Convierte entidad → DTO
    public static ShiftDto toDto(Shift entity) {
        if (entity == null) return null;

        ShiftDto dto = new ShiftDto();
        dto.setId(entity.getId());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        dto.setShiftDate(entity.getShiftDate());
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }

    // Convierte DTO → entidad
    public static Shift toEntity(ShiftDto dto) {
        if (dto == null) return null;

        Shift entity = new Shift();
        entity.setId(dto.getId());
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
        entity.setShiftDate(dto.getShiftDate());
        entity.setIsDeleted(dto.getIsDeleted() != null ? dto.getIsDeleted() : false);
        return entity;
    }
}