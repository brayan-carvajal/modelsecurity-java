package com.modelsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.modelsecurity.entity.Form;
import java.util.Optional;

@Repository
public interface FormRepository extends JpaRepository<Form, Integer> {
    Optional<Form> findByName(String name);
}
