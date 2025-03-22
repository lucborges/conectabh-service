package com.pucminas.conectabh_service.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Workspace {
    Integer id;
    String name;
    Integer capacity;
    String location;
    LocalDateTime updatedAt;
    LocalDateTime createdAt;

    public Workspace(String name, Integer capacity, String location, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.name = name;
        this.capacity = capacity;
        this.location = location;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }
}
