package com.modelsecurity.config;

import com.modelsecurity.entity.*;
import com.modelsecurity.repository.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                           PersonRepository personRepository,
                           RolRepository rolRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.personRepository = personRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        final String adminEmail = "admin@admin.com";
        final String adminPassword = "Admin123!"; // recommend changing in prod

        if (userRepository.existsByEmail(adminEmail)) {
            return;
        }

        // create person
        Person person = Person.builder()
                .firstName("Administrador")
                .lastName("Sistema")
                .documentType("N/A")
                .document("0000")
                .isDeleted(false)
                .build();
        person = personRepository.save(person);

        // create role
        Rol rol = Rol.builder()
                .name("ADMIN")
                .description("Rol administrador con todos los permisos")
                .isDeleted(false)
                .build();
        rol = rolRepository.save(rol);

        // create user
        User user = User.builder()
                .email(adminEmail)
                .password(passwordEncoder.encode(adminPassword))
                .registrationDate(LocalDateTime.now())
                .isDeleted(false)
                .person(person)
                .build();

        // create rol-user mapping and assign to user
        RolUser ru = RolUser.builder()
                .rol(rol)
                .user(user)
                .isDeleted(false)
                .build();

        user.setRolUser(List.of(ru));

        userRepository.save(user);
    }
}
