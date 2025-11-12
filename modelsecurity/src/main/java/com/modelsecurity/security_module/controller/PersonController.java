package com.modelsecurity.security_module.controller;

import com.modelsecurity.security_module.dto.PersonDto;
import com.modelsecurity.security_module.entity.Person;
import com.modelsecurity.security_module.mapper.PersonMapper;
import com.modelsecurity.security_module.security.UserSecurity;
import com.modelsecurity.security_module.service.interfaces.IBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
public class PersonController {

    private final IBaseService<Person> personService;
    private final UserSecurity userSecurity;

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PersonDto> getCurrentUserPerson() {
        Integer currentPersonId = userSecurity.getCurrentPersonId();
        if (currentPersonId == null) {
            return ResponseEntity.notFound().build();
        }
        return personService.findById(currentPersonId)
            .map(p -> ResponseEntity.ok(PersonMapper.toDto(p)))
            .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<PersonDto>> getAll() {
        List<PersonDto> list = personService.findAll()
                .stream()
                .map(PersonMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and @userSecurity.isCurrentUser(#id))")
    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getById(@PathVariable Integer id) {
        return personService.findById(id)
                .map(p -> ResponseEntity.ok(PersonMapper.toDto(p)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PersonDto> create(@RequestBody PersonDto dto) {
        Person entity = PersonMapper.toEntity(dto);
        Person saved = personService.save(entity);
        return ResponseEntity.ok(PersonMapper.toDto(saved));
    }

    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and @userSecurity.isCurrentUser(#id))")
    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> update(@PathVariable Integer id, @RequestBody PersonDto dto) {
        return personService.findById(id)
                .map(existing -> {
                    // Actualizamos solo los campos que no son null en el DTO
                    if (dto.getFirstName() != null) existing.setFirstName(dto.getFirstName());
                    if (dto.getLastName() != null) existing.setLastName(dto.getLastName());
                    if (dto.getMiddleName() != null) existing.setMiddleName(dto.getMiddleName());
                    if (dto.getSecondLastName() != null) existing.setSecondLastName(dto.getSecondLastName());
                    if (dto.getDocumentType() != null) existing.setDocumentType(dto.getDocumentType());
                    if (dto.getDocument() != null) existing.setDocument(dto.getDocument());
                    if (dto.getPhoneNumber() != null) existing.setPhoneNumber(dto.getPhoneNumber());
                    if (dto.getDateBorn() != null) existing.setDateBorn(dto.getDateBorn());
                    if (dto.getGender() != null) existing.setGender(dto.getGender());
                    if (dto.getEpsId() != null) existing.setEpsId(dto.getEpsId());
                    if (dto.getPersonExter() != null) existing.setPersonExter(dto.getPersonExter());
                    if (dto.getCityId() != null) existing.setCityId(dto.getCityId());
                    // Mantenemos el valor existente de isDeleted
                    // existing.setIsDeleted(existing.getIsDeleted());

                    Person updated = personService.update(id, existing);
                    return ResponseEntity.ok(PersonMapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            personService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
