package com.modelsecurity.cinema_module.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.modelsecurity.cinema_module.entity.TheaterRoom;

@Repository
public interface TheaterRoomRepository extends JpaRepository<TheaterRoom, Integer> {
}