package com.modelsecurity.security_module.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.modelsecurity.security_module.entity.RolUser;

@Repository
public interface RolUserRepository extends JpaRepository<RolUser, Integer> {
}
