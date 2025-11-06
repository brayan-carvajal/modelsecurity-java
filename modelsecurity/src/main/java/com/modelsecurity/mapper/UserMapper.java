package com.modelsecurity.mapper;

import com.modelsecurity.dto.UserDto;
import com.modelsecurity.entity.User;

public class UserMapper {

    // Convierte entidad → DTO
    public static UserDto toDto(User entity) {
        if (entity == null) return null;

        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setRegistrationDate(entity.getRegistrationDate());
        dto.setIsDeleted(entity.getIsDeleted());
        dto.setEnabled(entity.getEnabled());
        dto.setLocked(entity.getLocked());
        dto.setPersonId(
                entity.getPerson() != null ? entity.getPerson().getId() : null
        );
        return dto;
    }

    // Convierte DTO → entidad
    public static User toEntity(UserDto dto) {
        if (dto == null) return null;

        User entity = new User();
        entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setRegistrationDate(dto.getRegistrationDate());
        entity.setIsDeleted(dto.getIsDeleted());
        entity.setEnabled(dto.getEnabled() != null ? dto.getEnabled() : true);
        entity.setLocked(dto.getLocked() != null ? dto.getLocked() : false);
        return entity;
    }
}
