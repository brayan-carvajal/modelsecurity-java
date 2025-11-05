package com.modelsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.modelsecurity.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
}
