package com.modelsecurity.security_module.service.impl;

import com.modelsecurity.security_module.entity.Form;
import com.modelsecurity.security_module.repository.FormRepository;
import com.modelsecurity.security_module.service.interfaces.IFormService;
import org.springframework.stereotype.Service;

@Service
public class FormServiceImpl extends BaseServiceImpl<Form> implements IFormService {
    public FormServiceImpl(FormRepository repository) {
        super(repository);
    }
}
