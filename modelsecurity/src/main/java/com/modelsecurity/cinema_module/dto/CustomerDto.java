package com.modelsecurity.cinema_module.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
public class CustomerDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
    private String email;
    private String phoneNumber;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean isDeleted;
}