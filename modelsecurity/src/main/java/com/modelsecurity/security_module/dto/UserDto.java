package com.modelsecurity.security_module.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    @Email
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private LocalDateTime registrationDate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean isDeleted;
    // account state fields removed (managed by security defaults)
    private Integer personId; 
}
