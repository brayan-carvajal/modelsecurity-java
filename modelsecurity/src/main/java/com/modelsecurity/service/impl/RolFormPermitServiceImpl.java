package com.modelsecurity.service.impl;

import com.modelsecurity.entity.RolFormPermit;
import com.modelsecurity.repository.RolFormPermitRepository;
import com.modelsecurity.service.interfaces.IRolFormPermitService;
import org.springframework.stereotype.Service;

@Service
public class RolFormPermitServiceImpl extends BaseServiceImpl<RolFormPermit> implements IRolFormPermitService {
    public RolFormPermitServiceImpl(RolFormPermitRepository repository) {
        super(repository);
    }
}
