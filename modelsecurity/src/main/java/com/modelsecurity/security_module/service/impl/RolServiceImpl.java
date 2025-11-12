package com.modelsecurity.security_module.service.impl;

import com.modelsecurity.security_module.entity.Rol;
import com.modelsecurity.security_module.repository.RolRepository;
import com.modelsecurity.security_module.service.interfaces.IRolService;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl extends BaseServiceImpl<Rol> implements IRolService {

    public RolServiceImpl(RolRepository repository) {
        super(repository);
    }
}
