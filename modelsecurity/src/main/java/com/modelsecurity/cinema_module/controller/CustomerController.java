package com.modelsecurity.cinema_module.controller;

import com.modelsecurity.cinema_module.dto.CustomerDto;
import com.modelsecurity.cinema_module.entity.Customer;
import com.modelsecurity.cinema_module.mapper.CustomerMapper;
import com.modelsecurity.cinema_module.service.interfaces.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final ICustomerService customerService;

    // Obtener todos los clientes
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAll() {
        List<CustomerDto> list = customerService.findAll()
                .stream()
                .map(CustomerMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    // Obtener un cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getById(@PathVariable Integer id) {
        return customerService.findById(id)
                .map(customer -> ResponseEntity.ok(CustomerMapper.toDto(customer)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear cliente
    @PostMapping
    public ResponseEntity<CustomerDto> create(@Valid @RequestBody CustomerDto dto) {
        Customer entity = CustomerMapper.toEntity(dto);
        Customer saved = customerService.save(entity);
        return ResponseEntity.ok(CustomerMapper.toDto(saved));
    }

    // Actualizar cliente
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> update(@PathVariable Integer id, @Valid @RequestBody CustomerDto dto) {
        return customerService.findById(id)
                .map(existing -> {
                    existing.setFirstName(dto.getFirstName());
                    existing.setLastName(dto.getLastName());
                    existing.setEmail(dto.getEmail());
                    existing.setPhoneNumber(dto.getPhoneNumber());
                    existing.setIsDeleted(dto.getIsDeleted());

                    Customer updated = customerService.update(id, existing);
                    return ResponseEntity.ok(CustomerMapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            customerService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}