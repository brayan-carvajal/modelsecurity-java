package com.modelsecurity.cinema_module.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class TheaterRoomDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    @NotBlank
    private String name;
    private Integer capacity;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean isDeleted;
}