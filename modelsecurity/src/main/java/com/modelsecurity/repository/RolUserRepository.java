package com.modelsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.modelsecurity.entity.RolUser;

@Repository
public interface RolUserRepository extends JpaRepository<RolUser, Integer> {
}
