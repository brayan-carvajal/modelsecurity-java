package com.modelsecurity.security_module.service.impl;

import com.modelsecurity.security_module.entity.FormModule;
import com.modelsecurity.security_module.repository.FormModuleRepository;
import com.modelsecurity.security_module.service.interfaces.IFormModuleService;
import org.springframework.stereotype.Service;

@Service
public class FormModuleServiceImpl extends BaseServiceImpl<FormModule> implements IFormModuleService {
    public FormModuleServiceImpl(FormModuleRepository repository) {
        super(repository);
    }
}
