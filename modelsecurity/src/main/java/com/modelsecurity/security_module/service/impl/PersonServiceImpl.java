package com.modelsecurity.security_module.service.impl;

import com.modelsecurity.security_module.entity.Person;
import com.modelsecurity.security_module.repository.PersonRepository;
import com.modelsecurity.security_module.service.interfaces.IPersonService;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl extends BaseServiceImpl<Person> implements IPersonService {

    public PersonServiceImpl(PersonRepository repository) {
        super(repository);
    }
}
