package com.modelsecurity.cinema_module.service.impl;

import com.modelsecurity.cinema_module.entity.ScreeningEmployee;
import com.modelsecurity.cinema_module.repository.ScreeningEmployeeRepository;
import com.modelsecurity.cinema_module.service.interfaces.IScreeningEmployeeService;
import com.modelsecurity.security_module.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ScreeningEmployeeServiceImpl extends BaseServiceImpl<ScreeningEmployee> implements IScreeningEmployeeService {

    private final ScreeningEmployeeRepository screeningEmployeeRepository;

    public ScreeningEmployeeServiceImpl(ScreeningEmployeeRepository screeningEmployeeRepository) {
        super(screeningEmployeeRepository);
        this.screeningEmployeeRepository = screeningEmployeeRepository;
    }
}