package com.etec.backend.services.impl;

import com.etec.backend.entities.User;
import com.etec.backend.dtos.ResponseDTO;
import com.etec.backend.dtos.UserResponseDTO;
import com.etec.backend.repositories.UserRepository;
import com.etec.backend.services.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Object listId(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional
                .map(user -> new UserResponseDTO(user.getId(), user.getName(), user.getLastname(), user.getDob(),
                        user.getGender(), user.getPhoto()));
    }

    @Override
    public Object update(Long id, User user) {
        if (!userRepository.existsById(id)) {
            return new ResponseDTO("ERROR", "O ID especificado não existe: " + id);
        }

        user.setId(id);
        User updatedUser = userRepository.save(user);
        return new ResponseDTO("OK", "Usuário atualizado com sucesso: " + updatedUser.getName());
    }
}
