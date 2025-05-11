package com.pucminas.conectabh_service.controller;

import com.pucminas.conectabh_service.adapter.dtoToEntity.ReservationDtoToReservation;
import com.pucminas.conectabh_service.controller.dto.ReservationDto;
import com.pucminas.conectabh_service.controller.dto.UpdateReservationDto;
import com.pucminas.conectabh_service.controller.dto.WorkspaceReservationDto;
import com.pucminas.conectabh_service.controller.responses.ReservationResponse;
import com.pucminas.conectabh_service.usecase.ReservationUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{workspaceId}")
    public ResponseEntity<WorkspaceReservationDto> getReservationsByWorkspaceId(@PathVariable Integer workspaceId) {
        return ResponseEntity.ok(reservationUsecase.getReservationsByWorkspaceId(workspaceId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReservationResponse>> getReservationsByUser(@PathVariable Integer userId) {
        List<ReservationResponse> reservations = reservationUsecase.getByUserId(userId);
        return ResponseEntity.ok(reservations);
    }

    @PutMapping("/{id}")
    public void updateReservation(@PathVariable Integer id, @RequestBody UpdateReservationDto updateReservationDto) {
        reservationUsecase.update(id, updateReservationDto);
    }

    @GetMapping
    public ResponseEntity<List<WorkspaceReservationDto>> getReservationsByWorkspace() {
        return ResponseEntity.ok(reservationUsecase.getWorkspaceReservations());
    }
}
