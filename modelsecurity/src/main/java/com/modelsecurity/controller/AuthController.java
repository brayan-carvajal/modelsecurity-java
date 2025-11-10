package com.modelsecurity.controller;

import com.modelsecurity.dto.auth.LoginRequest;
import com.modelsecurity.dto.auth.LoginResponse;
import com.modelsecurity.dto.auth.RegisterRequest;
import com.modelsecurity.entity.Person;
import com.modelsecurity.entity.Rol;
import com.modelsecurity.entity.RolUser;
import com.modelsecurity.entity.User;
import com.modelsecurity.mapper.UserMapper;
import com.modelsecurity.repository.RolRepository;
import com.modelsecurity.repository.RolUserRepository;
import com.modelsecurity.security.JwtTokenProvider;
import com.modelsecurity.service.interfaces.IUserService;
import com.modelsecurity.service.interfaces.IBaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Value;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IUserService userService;
    private final IBaseService<com.modelsecurity.entity.Person> personService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RolRepository rolRepository;
    private final RolUserRepository rolUserRepository;
    @Value("${jwt.expiration-ms}")
    private long jwtExpirationMs;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        // Verificar si el email ya existe
        if (userService.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email ya registrado");
        }

        try {
            // 1. Crear la Person
            Person person = Person.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .documentType(request.getDocumentType())
                    .document(request.getDocument())
                    .isDeleted(false)
                    .build();
            person = personService.save(person);

            // 2. Crear el User
        User user = User.builder()
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .registrationDate(LocalDateTime.now())
            .isDeleted(false)
            .person(person)
            .build();
            
            // 3. Guardar el User
            User saved = userService.save(user);

            // 4. Asignar el rol USER por defecto
            Rol userRol = rolRepository.findByName("USER")
                    .orElseThrow(() -> new RuntimeException("Rol USER no encontrado"));
            
            RolUser rolUser = RolUser.builder()
                    .rol(userRol)
                    .user(saved)
                    .isDeleted(false)
                    .build();
            rolUserRepository.save(rolUser);

            return ResponseEntity.ok(UserMapper.toDto(saved));
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        var userOpt = userService.findByEmail(request.getEmail());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
        User user = userOpt.get();
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
        if (Boolean.TRUE.equals(user.getIsDeleted())) {
            return ResponseEntity.status(403).body("Cuenta eliminada/deshabilitada");
        }
        String token = jwtTokenProvider.generateToken(user.getEmail());
        return ResponseEntity.ok(new LoginResponse(token, "Bearer", jwtExpirationMs / 1000));
    }
}
