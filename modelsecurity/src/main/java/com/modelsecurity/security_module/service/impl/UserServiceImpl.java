package com.modelsecurity.security_module.service.impl;

import com.modelsecurity.security_module.entity.User;
import com.modelsecurity.security_module.repository.UserRepository;
import com.modelsecurity.security_module.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
