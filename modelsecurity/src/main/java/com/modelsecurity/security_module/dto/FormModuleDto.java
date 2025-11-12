package com.modelsecurity.security_module.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FormModuleDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    private Integer formId;
    private Integer moduleId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean isDeleted;
}
