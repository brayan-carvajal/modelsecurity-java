package com.modelsecurity.cinema_module.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReservationDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    private Integer customerId;
    private Integer screeningId;
    private LocalDateTime reservationDate;
    private String status;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean isDeleted;
}