package com.modelsecurity.service.impl;

import com.modelsecurity.entity.FormModule;
import com.modelsecurity.repository.FormModuleRepository;
import com.modelsecurity.service.interfaces.IFormModuleService;
import org.springframework.stereotype.Service;

@Service
public class FormModuleServiceImpl extends BaseServiceImpl<FormModule> implements IFormModuleService {
    public FormModuleServiceImpl(FormModuleRepository repository) {
        super(repository);
    }
}
