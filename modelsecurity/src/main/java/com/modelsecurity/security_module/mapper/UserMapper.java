package com.modelsecurity.security_module.mapper;

import com.modelsecurity.security_module.dto.UserDto;
import com.modelsecurity.security_module.entity.User;

public class UserMapper {

    // Convierte entidad → DTO
    public static UserDto toDto(User entity) {
        if (entity == null) return null;

        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setRegistrationDate(entity.getRegistrationDate());
    dto.setIsDeleted(entity.getIsDeleted());
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
    entity.setIsDeleted(dto.getIsDeleted() != null ? dto.getIsDeleted() : false);
        return entity;
    }
}
