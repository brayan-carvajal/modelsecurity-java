package com.modelsecurity.cinema_module.mapper;

import com.modelsecurity.cinema_module.dto.EmployeeDto;
import com.modelsecurity.cinema_module.entity.Employee;

public class EmployeeMapper {

    // Convierte entidad → DTO
    public static EmployeeDto toDto(Employee entity) {
        if (entity == null) return null;

        EmployeeDto dto = new EmployeeDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPosition(entity.getPosition());
        dto.setHireDate(entity.getHireDate());
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }

    // Convierte DTO → entidad
    public static Employee toEntity(EmployeeDto dto) {
        if (dto == null) return null;

        Employee entity = new Employee();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPosition(dto.getPosition());
        entity.setHireDate(dto.getHireDate());
        entity.setIsDeleted(dto.getIsDeleted() != null ? dto.getIsDeleted() : false);
        return entity;
    }
}