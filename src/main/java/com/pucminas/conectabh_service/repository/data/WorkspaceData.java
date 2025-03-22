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
@Table(name = "workspace")
public class WorkspaceData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workspace_id")
    private Integer id;
    @Column(name = "workspace_name")
    private String name;
    @Column(name = "capacity")
    private Integer capacity;
    @Column(name = "location")
    private String location;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
