package com.modelsecurity.service.interfaces;

import com.modelsecurity.entity.User;
import java.util.Optional;

public interface IUserService extends IBaseService<User> {
    Optional<User> findByEmail(String email);
}
