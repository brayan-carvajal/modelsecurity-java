package com.modelsecurity.service.impl;

import com.modelsecurity.entity.Person;
import com.modelsecurity.repository.PersonRepository;
import com.modelsecurity.service.interfaces.IPersonService;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl extends BaseServiceImpl<Person> implements IPersonService {

    public PersonServiceImpl(PersonRepository repository) {
        super(repository);
    }
}
