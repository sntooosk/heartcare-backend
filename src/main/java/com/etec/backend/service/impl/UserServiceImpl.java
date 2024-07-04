package com.etec.backend.service.impl;

import com.etec.backend.dto.ResponseDTO;
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

    @Override
    public List<UserResponseDTO> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserResponseDTO(user.getId(), user.getName(), user.getLastname(), user.getDob(),
                        user.getGender(), user.getPhoto()))
                .collect(Collectors.toList());
    }

    @Override
    public Object listId(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional
                .map(user -> new UserResponseDTO(user.getId(), user.getName(), user.getLastname(), user.getDob(),
                        user.getGender(), user.getPhoto()))
                .orElse(null);
    }

    @Override
    public UserResponseDTO update(Long id, User user) {
        if (!userRepository.existsById(id)) {
            return null; // Handle the case where user with given id does not exist
        }
        user.setId(id);
        User updatedUser = userRepository.save(user);
        return new UserResponseDTO(updatedUser.getId(), updatedUser.getName(), updatedUser.getLastname(),
                updatedUser.getDob(), updatedUser.getGender(), updatedUser.getPhoto());
    }

    @Override
    public ResponseDTO delete(Long id) {
        if (!userRepository.existsById(id)) {
            return new ResponseDTO("Não existe esse ID.");
        }
        userRepository.deleteById(id);
        return new ResponseDTO("Removido com sucesso.");
    }
}
