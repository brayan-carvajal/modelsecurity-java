package com.modelsecurity.service.impl;

import com.modelsecurity.entity.Rol;
import com.modelsecurity.repository.RolRepository;
import com.modelsecurity.service.interfaces.IRolService;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl extends BaseServiceImpl<Rol> implements IRolService {

    public RolServiceImpl(RolRepository repository) {
        super(repository);
    }
}
