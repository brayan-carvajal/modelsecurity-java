package com.modelsecurity.dto;

import lombok.Data;

@Data
public class PermissionDto {
    private Integer id;
    private String name;
    private String description;
    private Boolean isDeleted;
}
