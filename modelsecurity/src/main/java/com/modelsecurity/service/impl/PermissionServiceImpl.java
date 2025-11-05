package com.modelsecurity.service.impl;

import com.modelsecurity.entity.Permission;
import com.modelsecurity.repository.PermissionRepository;
import com.modelsecurity.service.interfaces.IPermissionService;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements IPermissionService {

    public PermissionServiceImpl(PermissionRepository repository) {
        super(repository);
    }
}
