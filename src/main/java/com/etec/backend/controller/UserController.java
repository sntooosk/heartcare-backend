package com.etec.backend.controller;

import com.etec.backend.dto.UserResponseDTO;
import com.etec.backend.entity.User;
import com.etec.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserResponseDTO> list() {
        return userService.list();
    }

    @GetMapping("/{id}")
    public UserResponseDTO listId(@PathVariable Long id) {
        return userService.listId(id);
    }

    @PutMapping("/{id}")
    public UserResponseDTO update(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }
}
