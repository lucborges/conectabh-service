package com.pucminas.conectabh_service.repository.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Table(name = "reservation")
public class ReservationData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "workspace_id")
    private WorkspaceData workspaceId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData userId;
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;
    @Column(name = "status")
    private String status;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
