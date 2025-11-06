package com.modelsecurity.controller;

import com.modelsecurity.dto.UserDto;
import com.modelsecurity.entity.Person;
import com.modelsecurity.entity.User;
import com.modelsecurity.mapper.UserMapper;
import com.modelsecurity.service.interfaces.IUserService;
import com.modelsecurity.service.interfaces.IBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    private final IUserService userService; 
    private final IBaseService<Person> personService; 
    
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
    public ResponseEntity<UserDto> create(@RequestBody UserDto dto) {
        User entity = UserMapper.toEntity(dto);

        if (dto.getPersonId() != null) {
            personService.findById(dto.getPersonId()).ifPresent(entity::setPerson);
        }

        User saved = userService.save(entity);
        return ResponseEntity.ok(UserMapper.toDto(saved));
    }

    // Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Integer id, @RequestBody UserDto dto) {
        return userService.findById(id)
                .map(existing -> {
                    existing.setEmail(dto.getEmail());
                    existing.setPassword(dto.getPassword());
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
