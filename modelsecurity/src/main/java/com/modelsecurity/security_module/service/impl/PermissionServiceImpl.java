package com.modelsecurity.security_module.service.impl;

import com.modelsecurity.security_module.entity.Permission;
import com.modelsecurity.security_module.repository.PermissionRepository;
import com.modelsecurity.security_module.service.interfaces.IPermissionService;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements IPermissionService {

    public PermissionServiceImpl(PermissionRepository repository) {
        super(repository);
    }
}
