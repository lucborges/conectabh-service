package com.pucminas.conectabh_service.usecase;

import com.pucminas.conectabh_service.adapter.entityToData.WorkspaceToWorkspaceData;
import com.pucminas.conectabh_service.domain.Workspace;
import com.pucminas.conectabh_service.repository.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkspaceUsecaseImpl implements WorkspaceUsecase {
    @Autowired
    WorkspaceRepository workspaceRepository;
    @Autowired
    WorkspaceToWorkspaceData adapter;

    @Override
    public void create(Workspace workspace) {
        workspaceRepository.save(adapter.convert(workspace));
    }
}
