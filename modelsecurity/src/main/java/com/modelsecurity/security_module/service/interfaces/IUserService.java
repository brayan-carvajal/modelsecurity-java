package com.modelsecurity.security_module.service.interfaces;

import com.modelsecurity.security_module.entity.User;
import java.util.Optional;

public interface IUserService extends IBaseService<User> {
    Optional<User> findByEmail(String email);
}
