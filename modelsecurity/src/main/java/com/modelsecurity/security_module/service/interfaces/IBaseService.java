package com.modelsecurity.security_module.service.interfaces;

import java.util.List;
import java.util.Optional;

public interface IBaseService<T> {
    List<T> findAll();
    Optional<T> findById(Integer id);
    T save(T entity);
    T update(Integer id, T entity);
    void delete(Integer id);
}
