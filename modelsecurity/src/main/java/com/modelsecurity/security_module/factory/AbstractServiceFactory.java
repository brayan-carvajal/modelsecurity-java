package com.modelsecurity.security_module.factory;

import com.modelsecurity.security_module.service.interfaces.IBaseService;
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
