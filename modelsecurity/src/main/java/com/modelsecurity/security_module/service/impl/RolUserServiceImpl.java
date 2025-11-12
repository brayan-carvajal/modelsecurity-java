package com.modelsecurity.security_module.service.impl;

import com.modelsecurity.security_module.entity.RolUser;
import com.modelsecurity.security_module.repository.RolUserRepository;
import com.modelsecurity.security_module.service.interfaces.IRolUserService;
import org.springframework.stereotype.Service;

@Service
public class RolUserServiceImpl extends BaseServiceImpl<RolUser> implements IRolUserService {
    public RolUserServiceImpl(RolUserRepository repository) {
        super(repository);
    }
}
