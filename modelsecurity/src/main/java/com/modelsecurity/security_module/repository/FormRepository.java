package com.modelsecurity.security_module.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.modelsecurity.security_module.entity.Form;
import java.util.Optional;

@Repository
public interface FormRepository extends JpaRepository<Form, Integer> {
    Optional<Form> findByName(String name);
}
