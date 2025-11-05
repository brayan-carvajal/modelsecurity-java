package com.modelsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.modelsecurity.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
}
