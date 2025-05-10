package com.pucminas.conectabh_service.usecase;

import com.pucminas.conectabh_service.adapter.dataToEntity.ReservationDataToReservation;
import com.pucminas.conectabh_service.adapter.entityToData.ReservationToReservationData;
import com.pucminas.conectabh_service.controller.dto.WorkspaceReservationDto;
import com.pucminas.conectabh_service.domain.Reservation;
import com.pucminas.conectabh_service.repository.ReservationRepository;
import com.pucminas.conectabh_service.repository.WorkspaceRepository;
import com.pucminas.conectabh_service.repository.data.ReservationData;
import com.pucminas.conectabh_service.utils.enums.ReservationStatus;
import com.pucminas.conectabh_service.utils.enums.WorkspaceStatus;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReservationUsecaseImpl implements ReservationUsecase {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    WorkspaceRepository workspaceRepository;
    @Autowired
    ReservationToReservationData reservationToReservationData;
    @Autowired
    ReservationDataToReservation reservationDataToReservation;

    private static final Logger logger = LoggerFactory.getLogger(ReservationUsecaseImpl.class);
    
    @Override
    public void create(Reservation reservation) throws Exception {
        Integer workspaceId = reservation.getWorkspace().getId();
        LocalDate targetDate = reservation.getReservationDate();

        List<ReservationData> existingReservations = reservationRepository
                .findByWorkspaceIdAndStatus(workspaceId, ReservationStatus.CONFIRMED);
        boolean existsSameDay = existingReservations.stream()
                .anyMatch(r -> r.getReservationDate().equals(targetDate));

        if (existsSameDay) {
            logger.error("There is already a confirmed reservation for this date. Cannot create the reservation");
            throw new Exception("There is already a confirmed reservation for this date. Cannot create the reservation");
        }

        if (!reservation.getWorkspace().getStatus().equals(WorkspaceStatus.AVAILABLE)) {
            reservation.setStatus(ReservationStatus.CANCELLED);
            logger.error("Workspace is not available for reservation.");
            throw new Exception("Workspace is not available for reservation.");
        }
        reservation.setStatus(ReservationStatus.CONFIRMED);
        reservation.getWorkspace().setStatus(WorkspaceStatus.RESERVED);
        reservationRepository.save(reservationToReservationData.convert(reservation));
        logger.info("Reservation create with success!");
    }

    @Override
    public Reservation get(Integer id) {
        return reservationDataToReservation.convert(reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found for id: " + id)));
    }

    @Override
    public void update(Integer id, Reservation newReservation) {
        ReservationData savedReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found for id: " + id));

        Integer workspaceId = savedReservation.getWorkspace().getId();
        LocalDate newDate = newReservation.getReservationDate();

        List<ReservationData> existingReservations = reservationRepository
                .findByWorkspaceIdAndStatus(workspaceId, ReservationStatus.CONFIRMED);

        boolean conflict = existingReservations.stream()
                .anyMatch(r -> !r.getId().equals(id) &&
                        r.getReservationDate().equals(newDate));

        if (conflict) {
            logger.error("There is already a confirmed reservation for this date. Cannot update the reservation");
            throw new IllegalStateException("There is already a confirmed reservation for this date. Cannot update the reservation");
        }

        savedReservation.setReservationDate(newReservation.getReservationDate());
        savedReservation.setStatus(newReservation.getStatus());

        reservationRepository.save(savedReservation);
    }

    @Override
    public void delete(Integer id) {
        ReservationData reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found for id: " + id));
        reservationRepository.delete(reservation);
        logger.info("Deleted the reservation: {}", id);
    }

    @Override
    public List<WorkspaceReservationDto> getWorkspaceReservations() {
        List<Object[]> rawData = reservationRepository.findConfirmedReservationDatesByWorkspace();

        Map<Integer, List<LocalDate>> grouped = new HashMap<>();

        for (Object[] row : rawData) {
            Integer workspaceId = (Integer) row[0];
            LocalDate date = (LocalDate) row[1];

            grouped.computeIfAbsent(workspaceId, k -> new ArrayList<>()).add(date);
        }

        return grouped.entrySet().stream()
                .map(entry -> new WorkspaceReservationDto(entry.getKey(), entry.getValue()))
                .toList();
    }
}
