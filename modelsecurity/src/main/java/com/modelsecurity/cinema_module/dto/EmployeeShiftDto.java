package com.modelsecurity.cinema_module.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EmployeeShiftDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    private Integer employeeId;
    private Integer shiftId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean isDeleted;
}