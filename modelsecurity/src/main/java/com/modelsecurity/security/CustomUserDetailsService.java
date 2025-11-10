package com.modelsecurity.security;

import com.modelsecurity.entity.User;
import com.modelsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
        @Transactional(readOnly = true)
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        // Authorities: derivar de roles asociados (por ahora solo nombre de rol)
                Collection<GrantedAuthority> authorities = new java.util.HashSet<>();
                if (user.getRolUser() != null) {
                        user.getRolUser().forEach(ru -> {
                                if (ru.getRol() != null && ru.getRol().getName() != null) {
                                        authorities.add(new SimpleGrantedAuthority("ROLE_" + ru.getRol().getName().toUpperCase()));
                                        // Permisos por formulario asociados al rol -> PERM:<FORM_NAME>:<PERMISSION_NAME>
                                        if (ru.getRol().getRolFormPermit() != null) {
                                                ru.getRol().getRolFormPermit().forEach(rfp -> {
                                                        String formName = rfp.getForm() != null ? rfp.getForm().getName() : null;
                                                        String permName = rfp.getPermission() != null ? rfp.getPermission().getName() : null;
                                                        if (formName != null && permName != null) {
                                                                authorities.add(new SimpleGrantedAuthority(
                                                                                "PERM:" + formName.toUpperCase() + ":" + permName.toUpperCase()
                                                                ));
                                                        }
                                                });
                                        }
                                }
                        });
                }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(authorities)
                // enabled/locked removed from entity; use defaults: account not locked, enabled
                .accountLocked(false)
                .disabled(false)
                .build();
    }
}
