package com.pucminas.conectabh_service.adapter.entityToData;

import com.pucminas.conectabh_service.adapter.Adapter;
import com.pucminas.conectabh_service.domain.Workspace;
import com.pucminas.conectabh_service.repository.data.WorkspaceData;
import org.springframework.stereotype.Component;

@Component
public class WorkspaceToWorkspaceData implements Adapter<Workspace, WorkspaceData> {
    @Override
    public WorkspaceData convert(Workspace workspace) {
        return new WorkspaceData(
                workspace.getId(),
                workspace.getName(),
                workspace.getCapacity(),
                workspace.getLocation(),
                workspace.getUpdatedAt(),
                workspace.getCreatedAt()
        );
    }
}
