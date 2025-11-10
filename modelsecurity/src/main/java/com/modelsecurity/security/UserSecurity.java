package com.modelsecurity.security;

import com.modelsecurity.entity.RolUser;
import com.modelsecurity.entity.User;
import com.modelsecurity.service.interfaces.IBaseService;
import com.modelsecurity.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("userSecurity")
@RequiredArgsConstructor
public class UserSecurity {

    private final IUserService userService;
    private final IBaseService<RolUser> rolUserService;

    public Integer getCurrentPersonId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        // Obtener el usuario actual por su email (username)
        User currentUser = userService.findByEmail(currentUsername).orElse(null);
        if (currentUser == null || currentUser.getPerson() == null) {
            return null;
        }

        return currentUser.getPerson().getId();
    }

    public boolean isCurrentUser(Integer personId) {
        Integer currentPersonId = getCurrentPersonId();
        if (currentPersonId == null) {
            return false;
        }
        return currentPersonId.equals(personId);
    }

    public boolean isUserRolOwner(Integer rolUserId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        // Obtener el usuario actual por su email (username)
        User currentUser = userService.findByEmail(currentUsername).orElse(null);
        if (currentUser == null) {
            return false;
        }

        // Buscar el RolUser y verificar si pertenece al usuario actual
        return rolUserService.findById(rolUserId)
            .map(rolUser -> rolUser.getUser().getId().equals(currentUser.getId()))
            .orElse(false);
    }
}