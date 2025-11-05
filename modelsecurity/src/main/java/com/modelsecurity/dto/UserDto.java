package com.modelsecurity.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserDto {
    private Integer id;
    private String email;
    private String password;
    private LocalDateTime registrationDate;
    private Boolean isDeleted;
    private Integer personId; 
}
