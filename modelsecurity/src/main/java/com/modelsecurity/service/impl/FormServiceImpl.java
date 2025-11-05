package com.modelsecurity.service.impl;

import com.modelsecurity.entity.Form;
import com.modelsecurity.repository.FormRepository;
import com.modelsecurity.service.interfaces.IFormService;
import org.springframework.stereotype.Service;

@Service
public class FormServiceImpl extends BaseServiceImpl<Form> implements IFormService {
    public FormServiceImpl(FormRepository repository) {
        super(repository);
    }
}
