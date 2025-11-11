package com.modelsecurity.mapper;

import com.modelsecurity.dto.RolFormPermitDto;
import com.modelsecurity.entity.RolFormPermit;

public class RolFormPermitMapper {

    public static RolFormPermitDto toDto(RolFormPermit entity) {
        if (entity == null) return null;

        RolFormPermitDto dto = new RolFormPermitDto();
        dto.setId(entity.getId());
        dto.setIsDeleted(entity.getIsDeleted());
        dto.setRolId(entity.getRol() != null ? entity.getRol().getId() : null);
        dto.setFormId(entity.getForm() != null ? entity.getForm().getId() : null);
        dto.setPermissionId(entity.getPermission() != null ? entity.getPermission().getId() : null);
        return dto;
    }

    public static RolFormPermit toEntity(RolFormPermitDto dto) {
        if (dto == null) return null;

        RolFormPermit entity = new RolFormPermit();
        entity.setId(dto.getId());
        entity.setIsDeleted(dto.getIsDeleted() != null ? dto.getIsDeleted() : false);
        return entity;
    }
}
