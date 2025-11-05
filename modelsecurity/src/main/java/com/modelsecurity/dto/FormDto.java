package com.modelsecurity.dto;

import lombok.Data;

@Data
public class FormDto {
    private Integer id;
    private String name;
    private String description;
    private Boolean isDeleted;
}
