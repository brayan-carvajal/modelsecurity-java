package com.modelsecurity.dto;

import lombok.Data;

@Data
public class FormModuleDto {
    private Integer id;
    private Boolean isDeleted;
    private Integer formId;
    private Integer moduleId;
}
