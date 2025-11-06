package com.modelsecurity.dto;

public record RegisterRequest(
        String email,
        String password,
        String firstName,
        String lastName,
        String documentType,
        String document
) { }
