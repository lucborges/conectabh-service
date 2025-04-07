package com.pucminas.conectabh_service.controller.dto;

import com.pucminas.conectabh_service.utils.enums.WorkspaceStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkspaceDto {
    private String name;
    private Integer capacity;
    private String location;
    private WorkspaceStatus status;
}
