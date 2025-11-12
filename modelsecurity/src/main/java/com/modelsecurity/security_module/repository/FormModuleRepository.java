package com.modelsecurity.security_module.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.modelsecurity.security_module.entity.FormModule;

@Repository
public interface FormModuleRepository extends JpaRepository<FormModule, Integer> {
}
