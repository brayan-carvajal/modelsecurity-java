package com.modelsecurity.cinema_module.service.impl;

import com.modelsecurity.cinema_module.entity.TheaterRoom;
import com.modelsecurity.cinema_module.repository.TheaterRoomRepository;
import com.modelsecurity.cinema_module.service.interfaces.ITheaterRoomService;
import com.modelsecurity.security_module.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TheaterRoomServiceImpl extends BaseServiceImpl<TheaterRoom> implements ITheaterRoomService {

    private final TheaterRoomRepository theaterRoomRepository;

    public TheaterRoomServiceImpl(TheaterRoomRepository theaterRoomRepository) {
        super(theaterRoomRepository);
        this.theaterRoomRepository = theaterRoomRepository;
    }
}