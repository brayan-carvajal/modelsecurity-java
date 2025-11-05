package com.modelsecurity.mapper;

import com.modelsecurity.dto.RolUserDto;
import com.modelsecurity.entity.RolUser;

public class RolUserMapper {

    public static RolUserDto toDto(RolUser entity) {
        if (entity == null) return null;

        RolUserDto dto = new RolUserDto();
        dto.setId(entity.getId());
        dto.setIsDeleted(entity.getIsDeleted());
        dto.setRolId(entity.getRol() != null ? entity.getRol().getId() : null);
        dto.setUserId(entity.getUser() != null ? entity.getUser().getId() : null);
        return dto;
    }

    public static RolUser toEntity(RolUserDto dto) {
        if (dto == null) return null;

        RolUser entity = new RolUser();
        entity.setId(dto.getId());
        entity.setIsDeleted(dto.getIsDeleted());
        return entity;
    }
}
