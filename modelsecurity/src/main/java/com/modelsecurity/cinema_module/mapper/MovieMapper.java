package com.modelsecurity.cinema_module.mapper;

import com.modelsecurity.cinema_module.dto.MovieDto;
import com.modelsecurity.cinema_module.entity.Movie;

public class MovieMapper {

    // Convierte entidad → DTO
    public static MovieDto toDto(Movie entity) {
        if (entity == null) return null;

        MovieDto dto = new MovieDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setDuration(entity.getDuration());
        dto.setReleaseDate(entity.getReleaseDate());
        dto.setGenre(entity.getGenre());
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }

    // Convierte DTO → entidad
    public static Movie toEntity(MovieDto dto) {
        if (dto == null) return null;

        Movie entity = new Movie();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setDuration(dto.getDuration());
        entity.setReleaseDate(dto.getReleaseDate());
        entity.setGenre(dto.getGenre());
        entity.setIsDeleted(dto.getIsDeleted() != null ? dto.getIsDeleted() : false);
        return entity;
    }
}