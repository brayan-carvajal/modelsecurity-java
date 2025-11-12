package com.modelsecurity.cinema_module.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.modelsecurity.cinema_module.entity.ScreeningEmployee;

@Repository
public interface ScreeningEmployeeRepository extends JpaRepository<ScreeningEmployee, Integer> {
}