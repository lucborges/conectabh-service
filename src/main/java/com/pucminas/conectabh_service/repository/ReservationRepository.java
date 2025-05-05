package com.pucminas.conectabh_service.repository;

import com.pucminas.conectabh_service.repository.data.ReservationData;
import com.pucminas.conectabh_service.utils.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationData, Integer> {
    @Query("SELECT r.workspace.id, r.reservationDate " +
            "FROM ReservationData r " +
            "WHERE r.status = 'CONFIRMED'")
    List<Object[]> findConfirmedReservationDatesByWorkspace();
    List<ReservationData> findByWorkspaceIdAndStatus(Integer workspaceId, ReservationStatus status);
}
