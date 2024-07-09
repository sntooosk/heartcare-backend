package com.etec.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import com.etec.entities.UserAccount;
import com.etec.service.UserAccountService;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountService userAccountService;


    @GetMapping("/{id}")
    public Object listId(@PathVariable Integer id) {
        return userAccountService.listId(id);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable Integer id, @RequestBody UserAccount userAccount) {
        return userAccountService.update(id, userAccount);
    }
    
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable Integer id) {
        return userAccountService.delete(id);
    }
}
