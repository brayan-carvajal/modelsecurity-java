package com.modelsecurity.controller;

import com.modelsecurity.dto.PersonDto;
import com.modelsecurity.entity.Person;
import com.modelsecurity.mapper.PersonMapper;
import com.modelsecurity.service.interfaces.IBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
public class PersonController {

    private final IBaseService<Person> personService;

    @GetMapping
    public ResponseEntity<List<PersonDto>> getAll() {
        List<PersonDto> list = personService.findAll()
                .stream()
                .map(PersonMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getById(@PathVariable Integer id) {
        return personService.findById(id)
                .map(p -> ResponseEntity.ok(PersonMapper.toDto(p)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PersonDto> create(@RequestBody PersonDto dto) {
        Person entity = PersonMapper.toEntity(dto);
        Person saved = personService.save(entity);
        return ResponseEntity.ok(PersonMapper.toDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> update(@PathVariable Integer id, @RequestBody PersonDto dto) {
        return personService.findById(id)
                .map(existing -> {
                    existing.setFirstName(dto.getFirstName());
                    existing.setLastName(dto.getLastName());
                    existing.setMiddleName(dto.getMiddleName());
                    existing.setSecondLastName(dto.getSecondLastName());
                    existing.setDocumentType(dto.getDocumentType());
                    existing.setDocument(dto.getDocument());
                    existing.setPhoneNumber(dto.getPhoneNumber());
                    existing.setDateBorn(dto.getDateBorn());
                    existing.setGender(dto.getGender());
                    existing.setEpsId(dto.getEpsId());
                    existing.setPersonExter(dto.getPersonExter());
                    existing.setCityId(dto.getCityId());
                    existing.setIsDeleted(dto.getIsDeleted());

                    Person updated = personService.update(id, existing);
                    return ResponseEntity.ok(PersonMapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

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
