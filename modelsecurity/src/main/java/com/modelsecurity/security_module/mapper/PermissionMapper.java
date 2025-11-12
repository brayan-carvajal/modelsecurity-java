package com.modelsecurity.security_module.mapper;

import com.modelsecurity.security_module.dto.PermissionDto;
import com.modelsecurity.security_module.entity.Permission;

public class PermissionMapper {

    // Convierte entidad → DTO
    public static PermissionDto toDto(Permission entity) {
        if (entity == null) return null;

        PermissionDto dto = new PermissionDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }

    // Convierte DTO → entidad
    public static Permission toEntity(PermissionDto dto) {
        if (dto == null) return null;

        Permission entity = new Permission();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setIsDeleted(dto.getIsDeleted() != null ? dto.getIsDeleted() : false);
        return entity;
    }
}
