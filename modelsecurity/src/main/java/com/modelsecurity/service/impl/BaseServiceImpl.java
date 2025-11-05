package com.modelsecurity.service.impl;

import com.modelsecurity.service.interfaces.IBaseService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<T> implements IBaseService<T> {

    protected final JpaRepository<T, Integer> repository;

    protected BaseServiceImpl(JpaRepository<T, Integer> repository) {
        this.repository = repository;
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<T> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public T update(Integer id, T entity) {
        if (repository.existsById(id)) {
            return repository.save(entity);
        }
        throw new RuntimeException("Entity not found with id: " + id);
    }

    @Override
    public void delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Entity not found with id: " + id);
        }
    }
}
