package com.etec.backend.controller;

import org.springframework.web.bind.annotation.*;

import com.etec.backend.domain.user.UserProfile;
import com.etec.backend.service.UserProfileService;

import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("api/v1/profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/get")
    public List<UserProfile> listAll() {
        return userProfileService.listAll();
    }

    @PostMapping("/create")
    public UserProfile create(@RequestBody UserProfile userProfile) {
        return userProfileService.create(userProfile);
    }

    @PutMapping("/update/{id}")
    public UserProfile update(@PathVariable Long id, @RequestBody UserProfile userProfile) {
        userProfile.setId(id);
        return userProfileService.update(userProfile);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        userProfileService.delete(id);
    }
}
