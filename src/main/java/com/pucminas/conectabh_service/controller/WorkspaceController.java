package com.pucminas.conectabh_service.controller;

import com.pucminas.conectabh_service.adapter.dtoToEntity.WorkspaceDtoToWorkspace;
import com.pucminas.conectabh_service.controller.dto.WorkspaceDto;
import com.pucminas.conectabh_service.usecase.WorkspaceUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkspaceController {
    @Autowired
    WorkspaceUsecase workspaceUsecase;
    @Autowired
    WorkspaceDtoToWorkspace adapter;

    @PostMapping("/workspace/create")
    public void createWorkspace(WorkspaceDto workspaceDto) {
        workspaceUsecase.create(adapter.convert(workspaceDto));
    }
}
