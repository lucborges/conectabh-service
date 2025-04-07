package com.pucminas.conectabh_service.controller;

import com.pucminas.conectabh_service.adapter.dtoToEntity.ReservationDtoToReservation;
import com.pucminas.conectabh_service.controller.dto.ReservationDto;
import com.pucminas.conectabh_service.domain.Reservation;
import com.pucminas.conectabh_service.usecase.ReservationUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/reservation")
@RestController
public class ReservationController {
    @Autowired
    ReservationUsecase reservationUsecase;
    @Autowired
    ReservationDtoToReservation adapter;

    @PostMapping("/create")
    public void createReservation(@RequestBody ReservationDto reservationDto) throws Exception {
        reservationUsecase.create(adapter.convert(reservationDto));
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Integer id) {
        reservationUsecase.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable Integer id) {
        return ResponseEntity.ok(reservationUsecase.get(id));
    }

    @PutMapping("/{id}")
    public void updateReservation(@PathVariable Integer id, @RequestBody ReservationDto reservationDto) {
        reservationUsecase.update(id, adapter.convert(reservationDto));
    }
}
