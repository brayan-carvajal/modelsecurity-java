package com.modelsecurity.controller;

import com.modelsecurity.dto.auth.LoginRequest;
import com.modelsecurity.dto.auth.LoginResponse;
import com.modelsecurity.dto.auth.RegisterRequest;
import com.modelsecurity.entity.User;
import com.modelsecurity.mapper.UserMapper;
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
    @Value("${jwt.expiration-ms}")
    private long jwtExpirationMs;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        if (userService.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email ya registrado");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRegistrationDate(LocalDateTime.now());
        user.setEnabled(true);
        user.setLocked(false);

        if (request.getPersonId() != null) {
            personService.findById(request.getPersonId()).ifPresent(user::setPerson);
        }
        if (user.getPerson() == null) {
            return ResponseEntity.badRequest().body("Debe asociar un personId válido");
        }

        User saved = userService.save(user);
        return ResponseEntity.ok(UserMapper.toDto(saved));
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
        if (!Boolean.TRUE.equals(user.getEnabled()) || Boolean.TRUE.equals(user.getLocked())) {
            return ResponseEntity.status(403).body("Cuenta deshabilitada o bloqueada");
        }
        String token = jwtTokenProvider.generateToken(user.getEmail());
        return ResponseEntity.ok(new LoginResponse(token, "Bearer", jwtExpirationMs / 1000));
    }
}
