package com.pucminas.conectabh_service.repository;

import com.pucminas.conectabh_service.repository.data.ReservationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationData, Integer> {
}
