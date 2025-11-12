package com.modelsecurity.security_module.controller;

import com.modelsecurity.security_module.dto.RolUserDto;
import com.modelsecurity.security_module.entity.Rol;
import com.modelsecurity.security_module.entity.RolUser;
import com.modelsecurity.security_module.entity.User;
import com.modelsecurity.security_module.mapper.RolUserMapper;
import com.modelsecurity.security_module.service.interfaces.IBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rol-users")
@RequiredArgsConstructor
public class RolUserController {

    private final IBaseService<RolUser> rolUserService;
    private final IBaseService<Rol> rolService;
    private final IBaseService<User> userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RolUserDto>> getAll() {
        List<RolUserDto> list = rolUserService.findAll()
                .stream()
                .map(RolUserMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and @userSecurity.isUserRolOwner(#id))")
    public ResponseEntity<RolUserDto> getById(@PathVariable Integer id) {
        return rolUserService.findById(id)
                .map(ru -> ResponseEntity.ok(RolUserMapper.toDto(ru)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RolUserDto> create(@RequestBody RolUserDto dto) {
        var rolOpt = rolService.findById(dto.getRolId());
        var userOpt = userService.findById(dto.getUserId());

        if (rolOpt.isEmpty() || userOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        RolUser entity = RolUserMapper.toEntity(dto);
        entity.setRol(rolOpt.get());
        entity.setUser(userOpt.get());

        RolUser saved = rolUserService.save(entity);
        return ResponseEntity.ok(RolUserMapper.toDto(saved));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            rolUserService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
