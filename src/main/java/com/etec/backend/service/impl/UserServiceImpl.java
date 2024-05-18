package com.etec.backend.service.impl;

import com.etec.backend.dto.UserResponseDTO;
import com.etec.backend.entity.User;
import com.etec.backend.repository.UserRepository;
import com.etec.backend.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public List<UserResponseDTO> list() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserResponseDTO(user.getId(), user.getName(), user.getLastName(), user.getDob(),
                        user.getGender(), user.getPhoto()))
                .collect(Collectors.toList());
    }

    public UserResponseDTO listId(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional
                .map(user -> new UserResponseDTO(user.getId(), user.getName(), user.getLastName(), user.getDob(),
                        user.getGender(),user.getPhoto()))
                .orElse(null);
    }

    public UserResponseDTO update(Long id, User user) {
        if (!userRepository.existsById(id)) {
            return null;
        }
        user.setId(id);
        User updatedUser = userRepository.save(user);
        return new UserResponseDTO(updatedUser.getId(), updatedUser.getName(), updatedUser.getLastName(),
                updatedUser.getDob(), updatedUser.getGender(), updatedUser.getPhoto());
    }
}
