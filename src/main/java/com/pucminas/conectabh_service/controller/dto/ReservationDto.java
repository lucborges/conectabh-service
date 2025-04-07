package com.pucminas.conectabh_service.controller.dto;

import com.pucminas.conectabh_service.utils.enums.ReservationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationDto {
    private Integer workspaceId;
    private Integer userId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ReservationStatus status;
}
