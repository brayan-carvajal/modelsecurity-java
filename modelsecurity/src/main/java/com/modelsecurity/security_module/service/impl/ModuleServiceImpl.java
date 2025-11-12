package com.modelsecurity.security_module.service.impl;

import com.modelsecurity.security_module.entity.Module;
import com.modelsecurity.security_module.repository.ModuleRepository;
import com.modelsecurity.security_module.service.interfaces.IModuleService;

import org.springframework.stereotype.Service;

@Service
public class ModuleServiceImpl extends BaseServiceImpl<Module> implements IModuleService {
    public ModuleServiceImpl(ModuleRepository repository) {
        super(repository);
    }
}
