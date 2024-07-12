package com.etec.backend.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.etec.backend.dto.RegisterRequestDTO;
import com.etec.backend.entity.AuthRole;
import com.etec.backend.service.AuthService;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @MockBean
    private AuthService authService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRegister() throws Exception {
        RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO("John Doe", "john.doe@example.com", "password123", AuthRole.USER);

        when(authService.register(registerRequestDTO)).thenReturn("User registered successfully");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"John Doe\", \"email\": \"john.doe@example.com\", \"password\": \"password123\", \"role\": \"USER\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("User registered successfully"));
    }
}
