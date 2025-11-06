package com.modelsecurity.service.impl;

import com.modelsecurity.entity.*;
import com.modelsecurity.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.modelsecurity.entity.User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        if (Boolean.TRUE.equals(user.getIsDeleted())) {
            throw new UsernameNotFoundException("Usuario eliminado");
        }

        // Construir autoridades a partir de RolUser -> Rol -> RolFormPermit -> Permission
        Set<String> authorities = new HashSet<>();

        List<RolUser> rolUsers = user.getRolUser();
        if (rolUsers != null) {
            for (RolUser ru : rolUsers) {
                if (ru.getRol() == null) continue;
                Rol rol = ru.getRol();
                if (rol.getName() != null) {
                    authorities.add("ROLE_" + rol.getName());
                }
                // permisos asociados a ese rol
                List<RolFormPermit> rfp = rol.getRolFormPermit();
                if (rfp != null) {
                    for (RolFormPermit p : rfp) {
                        if (p.getPermission() != null && p.getPermission().getName() != null) {
                            authorities.add(p.getPermission().getName());
                        }
                    }
                }
            }
        }

        List<GrantedAuthority> granted = authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                true, true, true, true,
                granted
        );
    }
}
