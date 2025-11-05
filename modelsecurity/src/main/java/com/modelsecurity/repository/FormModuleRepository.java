package com.modelsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.modelsecurity.entity.FormModule;

@Repository
public interface FormModuleRepository extends JpaRepository<FormModule, Integer> {
}
