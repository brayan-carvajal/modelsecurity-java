package com.modelsecurity.dto;

import lombok.Data;

@Data
public class RolFormPermitDto {
    private Integer id;
    private Boolean isDeleted;
    private Integer rolId;
    private Integer formId;
    private Integer permissionId;
}
