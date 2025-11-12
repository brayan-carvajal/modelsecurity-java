package com.modelsecurity.cinema_module.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShiftDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate shiftDate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean isDeleted;
}