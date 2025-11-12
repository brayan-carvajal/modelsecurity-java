package com.modelsecurity.cinema_module.service.impl;

import com.modelsecurity.cinema_module.entity.Customer;
import com.modelsecurity.cinema_module.repository.CustomerRepository;
import com.modelsecurity.cinema_module.service.interfaces.ICustomerService;
import com.modelsecurity.security_module.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer> implements ICustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        super(customerRepository);
        this.customerRepository = customerRepository;
    }
}