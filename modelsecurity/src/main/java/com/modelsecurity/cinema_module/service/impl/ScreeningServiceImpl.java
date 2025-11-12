package com.modelsecurity.cinema_module.service.impl;

import com.modelsecurity.cinema_module.entity.Screening;
import com.modelsecurity.cinema_module.repository.ScreeningRepository;
import com.modelsecurity.cinema_module.service.interfaces.IScreeningService;
import com.modelsecurity.security_module.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ScreeningServiceImpl extends BaseServiceImpl<Screening> implements IScreeningService {

    private final ScreeningRepository screeningRepository;

    public ScreeningServiceImpl(ScreeningRepository screeningRepository) {
        super(screeningRepository);
        this.screeningRepository = screeningRepository;
    }
}