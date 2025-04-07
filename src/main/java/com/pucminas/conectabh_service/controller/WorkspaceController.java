package com.pucminas.conectabh_service.controller;

import com.pucminas.conectabh_service.adapter.dtoToEntity.WorkspaceDtoToWorkspace;
import com.pucminas.conectabh_service.controller.dto.WorkspaceDto;
import com.pucminas.conectabh_service.repository.data.WorkspaceData;
import com.pucminas.conectabh_service.usecase.WorkspaceUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/workspace")
@RestController
public class WorkspaceController {
    @Autowired
    WorkspaceUsecase workspaceUsecase;
    @Autowired
    WorkspaceDtoToWorkspace adapter;

    @PostMapping("/create")
    public void createWorkspace(@RequestBody WorkspaceDto workspaceDto) {
        workspaceUsecase.create(adapter.convert(workspaceDto));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<WorkspaceData> getWorkspace(@PathVariable Integer id) {
        return ResponseEntity.ok(workspaceUsecase.get(id));
    }

    @PutMapping("/{id}")
    public void updateWorkspace(@PathVariable Integer id, @RequestBody WorkspaceDto workspaceDto) {
        workspaceUsecase.update(id, adapter.convert(workspaceDto));
    }

    @DeleteMapping("/{id}")
    public void deleteWorkspace(@PathVariable Integer id) {
        workspaceUsecase.delete(id);
    }
}
