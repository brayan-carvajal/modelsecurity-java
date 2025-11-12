package com.modelsecurity.security_module.controller;

import com.modelsecurity.security_module.dto.UserDto;
import com.modelsecurity.security_module.entity.Person;
import com.modelsecurity.security_module.entity.User;
import com.modelsecurity.security_module.mapper.UserMapper;
import com.modelsecurity.security_module.service.interfaces.IUserService;
import com.modelsecurity.security_module.service.interfaces.IBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    private final IUserService userService; 
    private final IBaseService<Person> personService; 
    private final PasswordEncoder passwordEncoder;
    
    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> list = userService.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Integer id) {
        return userService.findById(id)
                .map(user -> ResponseEntity.ok(UserMapper.toDto(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear usuario
    @PostMapping
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto dto) {
        User entity = UserMapper.toEntity(dto);

        if (dto.getPersonId() != null) {
            personService.findById(dto.getPersonId()).ifPresent(entity::setPerson);
        }

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        User saved = userService.save(entity);
        return ResponseEntity.ok(UserMapper.toDto(saved));
    }

    // Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Integer id, @Valid @RequestBody UserDto dto) {
        return userService.findById(id)
                .map(existing -> {
                    existing.setEmail(dto.getEmail());
                    if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
                        existing.setPassword(passwordEncoder.encode(dto.getPassword()));
                    }
                    existing.setRegistrationDate(dto.getRegistrationDate());
                    existing.setIsDeleted(dto.getIsDeleted());
                    if (dto.getPersonId() != null)
                        personService.findById(dto.getPersonId()).ifPresent(existing::setPerson);

                    User updated = userService.update(id, existing);
                    return ResponseEntity.ok(UserMapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            userService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
