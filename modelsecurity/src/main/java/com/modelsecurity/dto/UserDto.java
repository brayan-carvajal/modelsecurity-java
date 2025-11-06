package com.modelsecurity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserDto {
    private Integer id;
    @Email
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private LocalDateTime registrationDate;
    private Boolean isDeleted;
    private Boolean enabled;
    private Boolean locked;
    private Integer personId; 
}
