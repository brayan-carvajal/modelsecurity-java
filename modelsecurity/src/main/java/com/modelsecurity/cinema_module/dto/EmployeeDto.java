package com.modelsecurity.cinema_module.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class EmployeeDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String position;
    private LocalDate hireDate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean isDeleted;
}