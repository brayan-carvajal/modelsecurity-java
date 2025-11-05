package com.modelsecurity.dto;

import lombok.Data;

@Data
public class RolDto {
    private Integer id;
    private String name;
    private String description;
    private Boolean isDeleted;
}
