package com.pucminas.conectabh_service.usecase;

import com.pucminas.conectabh_service.domain.Reservation;

public interface ReservationUsecase {
    void create(Reservation reservation) throws Exception;
    Reservation get(Integer id);
    void update(Integer id, Reservation reservation);
    void delete(Integer id);
}
