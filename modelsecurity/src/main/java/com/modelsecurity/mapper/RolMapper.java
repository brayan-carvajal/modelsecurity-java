package com.modelsecurity.mapper;

import com.modelsecurity.dto.RolDto;
import com.modelsecurity.entity.Rol;

public class RolMapper {

    // Convierte entidad → DTO
    public static RolDto toDto(Rol entity) {
        if (entity == null) return null;

        RolDto dto = new RolDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }

    // Convierte DTO → entidad
    public static Rol toEntity(RolDto dto) {
        if (dto == null) return null;

        Rol entity = new Rol();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setIsDeleted(dto.getIsDeleted());
        return entity;
    }
}
