package com.modelsecurity.mapper;

import com.modelsecurity.dto.FormModuleDto;
import com.modelsecurity.entity.FormModule;

public class FormModuleMapper {

    public static FormModuleDto toDto(FormModule entity) {
        if (entity == null) return null;

        FormModuleDto dto = new FormModuleDto();
        dto.setId(entity.getId());
        dto.setIsDeleted(entity.getIsDeleted());
        dto.setFormId(entity.getForm() != null ? entity.getForm().getId() : null);
        dto.setModuleId(entity.getModule() != null ? entity.getModule().getId() : null);
        return dto;
    }

    public static FormModule toEntity(FormModuleDto dto) {
        if (dto == null) return null;

        FormModule entity = new FormModule();
        entity.setId(dto.getId());
        entity.setIsDeleted(dto.getIsDeleted() != null ? dto.getIsDeleted() : false);
        return entity;
    }
}
