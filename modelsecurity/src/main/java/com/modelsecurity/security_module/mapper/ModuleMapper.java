package com.modelsecurity.security_module.mapper;

import com.modelsecurity.security_module.dto.ModuleDto;
import com.modelsecurity.security_module.entity.Module;

public class ModuleMapper {

    // Convierte entidad → DTO
    public static ModuleDto toDto(Module entity) {
        if (entity == null) return null;

        ModuleDto dto = new ModuleDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }

    // Convierte DTO → entidad
    public static Module toEntity(ModuleDto dto) {
        if (dto == null) return null;

        Module entity = new Module();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setIsDeleted(dto.getIsDeleted() != null ? dto.getIsDeleted() : false);
        return entity;
    }
}
