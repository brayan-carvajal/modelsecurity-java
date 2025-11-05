package com.modelsecurity.service.impl;

import com.modelsecurity.entity.Module;
import com.modelsecurity.repository.ModuleRepository;
import com.modelsecurity.service.interfaces.IModuleService;

import org.springframework.stereotype.Service;

@Service
public class ModuleServiceImpl extends BaseServiceImpl<Module> implements IModuleService {
    public ModuleServiceImpl(ModuleRepository repository) {
        super(repository);
    }
}
