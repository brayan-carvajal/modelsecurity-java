package com.modelsecurity.cinema_module.mapper;

import com.modelsecurity.cinema_module.dto.EmployeeShiftDto;
import com.modelsecurity.cinema_module.entity.EmployeeShift;

public class EmployeeShiftMapper {

    // Convierte entidad → DTO
    public static EmployeeShiftDto toDto(EmployeeShift entity) {
        if (entity == null) return null;

        EmployeeShiftDto dto = new EmployeeShiftDto();
        dto.setId(entity.getId());
        dto.setEmployeeId(entity.getEmployee() != null ? entity.getEmployee().getId() : null);
        dto.setShiftId(entity.getShift() != null ? entity.getShift().getId() : null);
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }

    // Convierte DTO → entidad
    public static EmployeeShift toEntity(EmployeeShiftDto dto) {
        if (dto == null) return null;

        EmployeeShift entity = new EmployeeShift();
        entity.setId(dto.getId());
        entity.setIsDeleted(dto.getIsDeleted() != null ? dto.getIsDeleted() : false);
        return entity;
    }
}