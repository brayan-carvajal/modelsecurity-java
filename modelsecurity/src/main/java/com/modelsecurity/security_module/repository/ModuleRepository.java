package com.modelsecurity.security_module.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.modelsecurity.security_module.entity.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Integer> {
}
