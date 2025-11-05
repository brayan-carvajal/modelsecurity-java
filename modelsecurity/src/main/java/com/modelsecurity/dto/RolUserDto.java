package com.modelsecurity.dto;

import lombok.Data;

@Data
public class RolUserDto {
    private Integer id;
    private Boolean isDeleted;
    private Integer rolId;
    private Integer userId;
}
