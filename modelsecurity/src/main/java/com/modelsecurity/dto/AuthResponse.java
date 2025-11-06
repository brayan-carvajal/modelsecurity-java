package com.modelsecurity.dto;

import java.time.Instant;

public record AuthResponse(String token, String type, String username, Instant issuedAt) { }
