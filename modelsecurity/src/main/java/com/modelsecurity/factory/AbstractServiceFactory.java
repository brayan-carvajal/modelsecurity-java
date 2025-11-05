package com.modelsecurity.factory;

import com.modelsecurity.service.interfaces.IBaseService;
import org.springframework.stereotype.Component;

@Component
public class AbstractServiceFactory {

    private final ServiceFactory serviceFactory;

    public AbstractServiceFactory(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    public IBaseService<?> getServiceByName(String name) {
        try {
            ServiceType type = ServiceType.valueOf(name.toUpperCase());
            return serviceFactory.getService(type);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Tipo de servicio no reconocido: " + name);
        }
    }
}
