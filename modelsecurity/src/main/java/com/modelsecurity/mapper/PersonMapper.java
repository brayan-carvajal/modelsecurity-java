package com.modelsecurity.mapper;

import com.modelsecurity.dto.PersonDto;
import com.modelsecurity.entity.Person;

public class PersonMapper {

    // Convierte entidad → DTO
    public static PersonDto toDto(Person entity) {
        if (entity == null) return null;

        PersonDto dto = new PersonDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setMiddleName(entity.getMiddleName());
        dto.setLastName(entity.getLastName());
        dto.setSecondLastName(entity.getSecondLastName());
        dto.setDocumentType(entity.getDocumentType());
        dto.setDocument(entity.getDocument());
        dto.setDateBorn(entity.getDateBorn());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setGender(entity.getGender());
        dto.setPersonExter(entity.getPersonExter());
        dto.setEpsId(entity.getEpsId());
        dto.setCityId(entity.getCityId());
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }

    // Convierte DTO → entidad
    public static Person toEntity(PersonDto dto) {
        if (dto == null) return null;

        Person entity = new Person();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setMiddleName(dto.getMiddleName());
        entity.setLastName(dto.getLastName());
        entity.setSecondLastName(dto.getSecondLastName());
        entity.setDocumentType(dto.getDocumentType());
        entity.setDocument(dto.getDocument());
        entity.setDateBorn(dto.getDateBorn());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setGender(dto.getGender());
        entity.setPersonExter(dto.getPersonExter());
        entity.setEpsId(dto.getEpsId());
        entity.setCityId(dto.getCityId());
        entity.setIsDeleted(dto.getIsDeleted());
        return entity;
    }
}
