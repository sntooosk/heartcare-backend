package com.etec.backend.service;


import com.etec.backend.domain.user.UserProfile;


public interface UserProfileService {

    UserProfile findById(Long id);

    UserProfile create(UserProfile userProfile);

    UserProfile update(UserProfile userProfile);

    void delete(Long id);
}