package com.modelsecurity.cinema_module.mapper;

import com.modelsecurity.cinema_module.dto.TheaterRoomDto;
import com.modelsecurity.cinema_module.entity.TheaterRoom;

public class TheaterRoomMapper {

    // Convierte entidad → DTO
    public static TheaterRoomDto toDto(TheaterRoom entity) {
        if (entity == null) return null;

        TheaterRoomDto dto = new TheaterRoomDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCapacity(entity.getCapacity());
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }

    // Convierte DTO → entidad
    public static TheaterRoom toEntity(TheaterRoomDto dto) {
        if (dto == null) return null;

        TheaterRoom entity = new TheaterRoom();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setCapacity(dto.getCapacity());
        entity.setIsDeleted(dto.getIsDeleted() != null ? dto.getIsDeleted() : false);
        return entity;
    }
}