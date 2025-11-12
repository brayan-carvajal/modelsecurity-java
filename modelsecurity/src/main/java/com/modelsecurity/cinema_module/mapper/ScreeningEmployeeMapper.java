package com.modelsecurity.cinema_module.mapper;

import com.modelsecurity.cinema_module.dto.ScreeningEmployeeDto;
import com.modelsecurity.cinema_module.entity.ScreeningEmployee;

public class ScreeningEmployeeMapper {

    // Convierte entidad → DTO
    public static ScreeningEmployeeDto toDto(ScreeningEmployee entity) {
        if (entity == null) return null;

        ScreeningEmployeeDto dto = new ScreeningEmployeeDto();
        dto.setId(entity.getId());
        dto.setScreeningId(entity.getScreening() != null ? entity.getScreening().getId() : null);
        dto.setEmployeeId(entity.getEmployee() != null ? entity.getEmployee().getId() : null);
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }

    // Convierte DTO → entidad
    public static ScreeningEmployee toEntity(ScreeningEmployeeDto dto) {
        if (dto == null) return null;

        ScreeningEmployee entity = new ScreeningEmployee();
        entity.setId(dto.getId());
        entity.setIsDeleted(dto.getIsDeleted() != null ? dto.getIsDeleted() : false);
        return entity;
    }
}