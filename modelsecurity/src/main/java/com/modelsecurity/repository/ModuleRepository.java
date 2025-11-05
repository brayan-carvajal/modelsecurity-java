package com.modelsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.modelsecurity.entity.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Integer> {
}
