package com.pucminas.conectabh_service.usecase;

import com.pucminas.conectabh_service.adapter.dataToEntity.ReservationDataToReservation;
import com.pucminas.conectabh_service.adapter.entityToData.ReservationToReservationData;
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
        if (newReservation.getStartTime().isBefore(savedReservation.getStartTime())
                || newReservation.getEndTime().isBefore(savedReservation.getEndTime())) {
            throw new RuntimeException("The datetime cannot be less than what is scheduled");
        }
        savedReservation.setStartTime(newReservation.getStartTime());
        savedReservation.setEndTime(newReservation.getEndTime());
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
}
