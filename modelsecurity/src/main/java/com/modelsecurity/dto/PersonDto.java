package com.modelsecurity.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PersonDto {
    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String secondLastName;
    private String documentType;
    private String document;
    private LocalDate dateBorn;
    private String phoneNumber;
    private String gender;
    private String personExter;
    private String epsId;
    private Integer cityId;
    private Boolean isDeleted;
}
