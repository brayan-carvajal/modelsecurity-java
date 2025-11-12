package com.modelsecurity.cinema_module.mapper;

import com.modelsecurity.cinema_module.dto.CustomerDto;
import com.modelsecurity.cinema_module.entity.Customer;

public class CustomerMapper {

    // Convierte entidad → DTO
    public static CustomerDto toDto(Customer entity) {
        if (entity == null) return null;

        CustomerDto dto = new CustomerDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }

    // Convierte DTO → entidad
    public static Customer toEntity(CustomerDto dto) {
        if (dto == null) return null;

        Customer entity = new Customer();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setIsDeleted(dto.getIsDeleted() != null ? dto.getIsDeleted() : false);
        return entity;
    }
}