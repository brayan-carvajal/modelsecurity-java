package com.modelsecurity.cinema_module.service.impl;

import com.modelsecurity.cinema_module.entity.EmployeeShift;
import com.modelsecurity.cinema_module.repository.EmployeeShiftRepository;
import com.modelsecurity.cinema_module.service.interfaces.IEmployeeShiftService;
import com.modelsecurity.security_module.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EmployeeShiftServiceImpl extends BaseServiceImpl<EmployeeShift> implements IEmployeeShiftService {

    private final EmployeeShiftRepository employeeShiftRepository;

    public EmployeeShiftServiceImpl(EmployeeShiftRepository employeeShiftRepository) {
        super(employeeShiftRepository);
        this.employeeShiftRepository = employeeShiftRepository;
    }
}