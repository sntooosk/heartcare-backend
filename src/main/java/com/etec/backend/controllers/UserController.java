package com.etec.backend.controllers;

import com.etec.backend.entities.User;
import com.etec.backend.services.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public Object listId(@PathVariable Long id) {
        return userService.listId(id);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }
}
