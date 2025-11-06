package com.modelsecurity.controller;

import com.modelsecurity.config.JwtUtil;
import com.modelsecurity.dto.*;
import com.modelsecurity.entity.Person;
import com.modelsecurity.entity.User;
import com.modelsecurity.repository.PersonRepository;
import com.modelsecurity.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authManager,
                          JwtUtil jwtUtil,
                          UserRepository userRepository,
                          PersonRepository personRepository,
                          PasswordEncoder passwordEncoder) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email(), request.password())
            );

            org.springframework.security.core.userdetails.User userDetails =
                    (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

            var authorities = userDetails.getAuthorities().stream()
                    .map(a -> a.getAuthority()).collect(Collectors.toList());

            String token = jwtUtil.generateToken(userDetails.getUsername(), authorities);

            return ResponseEntity.ok(new AuthResponse(token, "Bearer", userDetails.getUsername(), Instant.now()));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        if (userRepository.existsByEmail(req.email())) {
            return ResponseEntity.badRequest().body("El email ya está registrado");
        }

        // crear person
        Person person = Person.builder()
                .firstName(req.firstName())
                .lastName(req.lastName())
                .documentType(req.documentType())
                .document(req.document())
                .isDeleted(false)
                .build();
        person = personRepository.save(person);

        // crear user
        User user = User.builder()
                .email(req.email())
                .password(passwordEncoder.encode(req.password()))
                .registrationDate(LocalDateTime.now())
                .isDeleted(false)
                .person(person)
                .build();

        userRepository.save(user);

        return ResponseEntity.ok("Usuario creado correctamente");
    }
}
