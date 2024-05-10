package com.etec.backend.service.Impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.etec.backend.domain.user.UserProfile;
import com.etec.backend.repository.UserProfileRepository;
import com.etec.backend.service.UserProfileService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Override
    public List<UserProfile> listAll() {
        return userProfileRepository.findAll();
    }

    @Override
    public UserProfile create(UserProfile userProfile) {
        if (userProfile.getId() != null) {
            throw new RuntimeException("To create a record, you cannot have an ID");
        }

        return userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile update(UserProfile userProfile) {
        if (userProfile.getId() == null) {
            throw new RuntimeException("To update a record, you must have an ID");
        }

        return userProfileRepository.save(userProfile);
    }

    @Override
    public void delete(Long id) {
        userProfileRepository.deleteById(id);
    }
}