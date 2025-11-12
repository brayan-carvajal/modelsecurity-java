package com.modelsecurity.cinema_module.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ScreeningDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    private Integer movieId;
    private Integer theaterRoomId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean isDeleted;
}