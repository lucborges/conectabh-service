package com.pucminas.conectabh_service.adapter.entityToData;

import com.pucminas.conectabh_service.adapter.Adapter;
import com.pucminas.conectabh_service.domain.Reservation;
import com.pucminas.conectabh_service.repository.UserRepository;
import com.pucminas.conectabh_service.repository.WorkspaceRepository;
import com.pucminas.conectabh_service.repository.data.ReservationData;
import com.pucminas.conectabh_service.repository.data.UserData;
import com.pucminas.conectabh_service.repository.data.WorkspaceData;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationToReservationData implements Adapter<Reservation, ReservationData> {
    @Autowired
    WorkspaceToWorkspaceData workspaceToWorkspaceData;
    @Autowired
    UserToUserData userToUserData;

    @Override
    public ReservationData convert(Reservation reservation) {
        ReservationData reservationData = new ReservationData();
        reservationData.setWorkspace(workspaceToWorkspaceData.convert(reservation.getWorkspace()));
        reservationData.setUser(userToUserData.convert(reservation.getUser()));
        reservationData.setStartTime(reservation.getStartTime());
        reservationData.setEndTime(reservation.getEndTime());
        reservationData.setStatus(reservation.getStatus());

        return reservationData;
    }
}
