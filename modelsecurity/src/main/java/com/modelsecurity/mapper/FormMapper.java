package com.modelsecurity.mapper;

import com.modelsecurity.dto.FormDto;
import com.modelsecurity.entity.Form;

public class FormMapper {

    public static FormDto toDto(Form entity) {
        if (entity == null) return null;

        FormDto dto = new FormDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }

    public static Form toEntity(FormDto dto) {
        if (dto == null) return null;

        Form entity = new Form();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setIsDeleted(dto.getIsDeleted());
        return entity;
    }
}
