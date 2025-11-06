package com.modelsecurity.modelsecurity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modelsecurity.dto.auth.LoginRequest;
import com.modelsecurity.dto.auth.RegisterRequest;
import com.modelsecurity.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    void clean() {
        userRepository.deleteAll();
    }

    @Test
    void loginFailsWithInvalidCredentials() throws Exception {
        // No usuario creado -> debe fallar
        LoginRequest login = new LoginRequest();
        login.setEmail("nouser@example.com");
        login.setPassword("badpass");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void registerFailsWithoutPersonId() throws Exception {
        RegisterRequest register = new RegisterRequest();
        register.setEmail("test@example.com");
        register.setPassword("Secret123!");
        // Sin personId -> debe fallar según lógica actual

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(register)))
                .andExpect(status().isBadRequest());
    }
}
