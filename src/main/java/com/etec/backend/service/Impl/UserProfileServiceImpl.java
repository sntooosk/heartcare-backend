package com.etec.backend.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.etec.backend.domain.user.UserProfile;
import com.etec.backend.repository.UserProfileRepository;
import com.etec.backend.service.UserProfileService;


@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;


    @Override
    public UserProfile findById(Long id) {
        return userProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil de usuário não encontrado com o ID: " + id));
    }

    @Override
    public UserProfile create(UserProfile userProfile) {
        if (userProfile.getId() != null) {
            throw new RuntimeException("Para criar um registro, você não pode ter um ID");
        }

        return userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile update(UserProfile userProfile) {
        if (userProfile.getId() == null) {
            throw new RuntimeException("Para atualizar um registro, você deve ter um ID");
        }

        return userProfileRepository.save(userProfile);
    }

    @Override
    public void delete(Long id) {
        userProfileRepository.deleteById(id);
    }
}
