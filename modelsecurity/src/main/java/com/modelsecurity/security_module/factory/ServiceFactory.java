package com.modelsecurity.security_module.factory;

import com.modelsecurity.security_module.service.interfaces.*;
import org.springframework.stereotype.Component;

@Component
public class ServiceFactory {

    private final IUserService userService;
    private final IPersonService personService;
    private final IRolService rolService;
    private final IPermissionService permissionService;
    private final IFormService formService;
    private final IModuleService moduleService;
    private final IFormModuleService formModuleService;
    private final IRolUserService rolUserService;
    private final IRolFormPermitService rolFormPermitService;

    public ServiceFactory(
            IUserService userService,
            IPersonService personService,
            IRolService rolService,
            IPermissionService permissionService,
            IFormService formService,
            IModuleService moduleService,
            IFormModuleService formModuleService,
            IRolUserService rolUserService,
            IRolFormPermitService rolFormPermitService
    ) {
        this.userService = userService;
        this.personService = personService;
        this.rolService = rolService;
        this.permissionService = permissionService;
        this.formService = formService;
        this.moduleService = moduleService;
        this.formModuleService = formModuleService;
        this.rolUserService = rolUserService;
        this.rolFormPermitService = rolFormPermitService;
    }

    public IBaseService<?> getService(ServiceType type) {
        return switch (type) {
            case USER -> userService;
            case PERSON -> personService;
            case ROL -> rolService;
            case PERMISSION -> permissionService;
            case FORM -> formService;
            case MODULE -> moduleService;
            case FORMMODULE -> formModuleService;
            case ROLUSER -> rolUserService;
            case ROLFORMPERMIT -> rolFormPermitService;
        };
    }
}
