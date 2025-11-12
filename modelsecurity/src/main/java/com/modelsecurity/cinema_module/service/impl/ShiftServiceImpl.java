package com.modelsecurity.cinema_module.service.impl;

import com.modelsecurity.cinema_module.entity.Shift;
import com.modelsecurity.cinema_module.repository.ShiftRepository;
import com.modelsecurity.cinema_module.service.interfaces.IShiftService;
import com.modelsecurity.security_module.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ShiftServiceImpl extends BaseServiceImpl<Shift> implements IShiftService {

    private final ShiftRepository shiftRepository;

    public ShiftServiceImpl(ShiftRepository shiftRepository) {
        super(shiftRepository);
        this.shiftRepository = shiftRepository;
    }
}