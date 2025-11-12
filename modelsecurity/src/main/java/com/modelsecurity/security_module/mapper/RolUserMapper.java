package com.modelsecurity.security_module.mapper;

import com.modelsecurity.security_module.dto.RolUserDto;
import com.modelsecurity.security_module.entity.RolUser;

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
        entity.setIsDeleted(dto.getIsDeleted() != null ? dto.getIsDeleted() : false);
        return entity;
    }
}
