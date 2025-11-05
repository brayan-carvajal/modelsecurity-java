package com.modelsecurity.service.impl;

import com.modelsecurity.entity.RolUser;
import com.modelsecurity.repository.RolUserRepository;
import com.modelsecurity.service.interfaces.IRolUserService;
import org.springframework.stereotype.Service;

@Service
public class RolUserServiceImpl extends BaseServiceImpl<RolUser> implements IRolUserService {
    public RolUserServiceImpl(RolUserRepository repository) {
        super(repository);
    }
}
