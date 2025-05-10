package com.pucminas.conectabh_service.usecase;

import com.pucminas.conectabh_service.controller.dto.WorkspaceReservationDto;
import com.pucminas.conectabh_service.domain.Reservation;

import java.util.List;

public interface ReservationUsecase {
    void create(Reservation reservation) throws Exception;
    Reservation get(Integer id);
    void update(Integer id, Reservation reservation);
    void delete(Integer id);
    List<WorkspaceReservationDto> getWorkspaceReservations();
}
