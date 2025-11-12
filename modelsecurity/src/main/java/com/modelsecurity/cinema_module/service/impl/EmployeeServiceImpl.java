package com.modelsecurity.cinema_module.service.impl;

import com.modelsecurity.cinema_module.entity.Employee;
import com.modelsecurity.cinema_module.repository.EmployeeRepository;
import com.modelsecurity.cinema_module.service.interfaces.IEmployeeService;
import com.modelsecurity.security_module.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements IEmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super(employeeRepository);
        this.employeeRepository = employeeRepository;
    }
}