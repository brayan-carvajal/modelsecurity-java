package com.modelsecurity.security_module.service.impl;

import com.modelsecurity.security_module.entity.RolFormPermit;
import com.modelsecurity.security_module.repository.RolFormPermitRepository;
import com.modelsecurity.security_module.service.interfaces.IRolFormPermitService;
import org.springframework.stereotype.Service;

@Service
public class RolFormPermitServiceImpl extends BaseServiceImpl<RolFormPermit> implements IRolFormPermitService {
    public RolFormPermitServiceImpl(RolFormPermitRepository repository) {
        super(repository);
    }
}
