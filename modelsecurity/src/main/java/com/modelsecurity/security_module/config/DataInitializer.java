package com.modelsecurity.security_module.config;

import com.modelsecurity.security_module.entity.*;
import com.modelsecurity.security_module.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private final RolRepository rolRepository;
    private final RolUserRepository rolUserRepository;
    private final FormRepository formRepository;
    private final PermissionRepository permissionRepository;
    private final RolFormPermitRepository rolFormPermitRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    @Transactional
    CommandLineRunner seedInitialData() {
        return args -> {
            // Rol USER por defecto
            rolRepository.findByName("USER").orElseGet(() -> {
                Rol r = Rol.builder().name("USER").description("Usuario").isDeleted(false).build();
                return rolRepository.save(r);
            });

            // Usuario admin por defecto
            final String adminEmail = "admin@gmail.com";
            userRepository.findByEmail(adminEmail).ifPresentOrElse(u -> {}, () -> {
                // Persona
                Person p = Person.builder()
                        .firstName("Admin")
                        .lastName("System")
                        .documentType("CC")
                        .document("1076501813")
                        .dateBorn(LocalDate.of(2005,3,14))
                        .phoneNumber("3188104661")
                        .gender("M")
                        .personExter("N")
                        .epsId("Nueva EPS")
                        .cityId(57)
                        .isDeleted(false)
                        .build();
                p = personRepository.save(p);

                // Rol ADMIN
                Rol adminRol = rolRepository.findByName("ADMIN").orElseGet(() -> {
                    Rol r = Rol.builder().name("ADMIN").description("Administrador").isDeleted(false).build();
                    return rolRepository.save(r);
                });

                // Usuario
                User admin = User.builder()
                        .email(adminEmail)
                        .password(passwordEncoder.encode("admin123"))
                        .registrationDate(LocalDateTime.now())
                        .isDeleted(false)
                        .person(p)
                        .build();
                admin = userRepository.save(admin);

                // Vincular rol a usuario
                RolUser ru = RolUser.builder()
                        .rol(adminRol)
                        .user(admin)
                        .isDeleted(false)
                        .build();
                rolUserRepository.save(ru);

                // Permisos básicos por formulario de ejemplo
                Form userForm = formRepository.findById(1).orElseGet(() ->
                        formRepository.save(Form.builder().name("USER_FORM").description("Gestión usuarios").isDeleted(false).build())
                );
                Permission readPerm = permissionRepository.findById(1).orElseGet(() ->
                        permissionRepository.save(Permission.builder().name("READ").description("Puede leer").isDeleted(false).build())
                );
                RolFormPermit rfp = RolFormPermit.builder()
                        .rol(adminRol)
                        .form(userForm)
                        .permission(readPerm)
                        .isDeleted(false)
                        .build();
                rolFormPermitRepository.save(rfp);
            });
        };
    }
}
