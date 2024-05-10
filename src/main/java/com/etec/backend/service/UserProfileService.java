package com.etec.backend.service;

import java.util.List;

import com.etec.backend.domain.user.UserProfile;


public interface UserProfileService {
    List<UserProfile> listAll();

    UserProfile create(UserProfile userProfile);

    UserProfile update(UserProfile userProfile);

    void delete(Long id);
}