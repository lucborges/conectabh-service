package com.pucminas.conectabh_service.domain;

import com.pucminas.conectabh_service.utils.enums.ReservationStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Reservation {
    private Integer id;
    private Workspace workspace;
    private User user;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ReservationStatus status;
}
