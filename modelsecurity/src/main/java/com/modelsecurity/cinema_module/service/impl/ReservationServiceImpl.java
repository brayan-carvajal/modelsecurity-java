package com.modelsecurity.cinema_module.service.impl;

import com.modelsecurity.cinema_module.entity.Reservation;
import com.modelsecurity.cinema_module.repository.ReservationRepository;
import com.modelsecurity.cinema_module.service.interfaces.IReservationService;
import com.modelsecurity.security_module.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl extends BaseServiceImpl<Reservation> implements IReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        super(reservationRepository);
        this.reservationRepository = reservationRepository;
    }
}