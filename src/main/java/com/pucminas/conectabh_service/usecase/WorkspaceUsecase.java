package com.pucminas.conectabh_service.usecase;

import com.pucminas.conectabh_service.domain.Workspace;
import com.pucminas.conectabh_service.repository.data.WorkspaceData;

public interface WorkspaceUsecase {
    void create(Workspace workspace);
    WorkspaceData get(Integer id);
    void update(Integer id, Workspace workspace);
    void delete(Integer id);
}
