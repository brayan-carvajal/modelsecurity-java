package com.modelsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.modelsecurity.entity.RolFormPermit;

@Repository
public interface RolFormPermitRepository extends JpaRepository<RolFormPermit, Integer> {
}
