package com.etec.backend.service.impl;

import com.etec.backend.dto.ResponseDTO;
import com.etec.backend.entity.User;
import com.etec.backend.repository.UserRepository;
import com.etec.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

        private final UserRepository userRepository;

        @Override
        public Object getUserById(Long id) {
                Optional<User> userOptional = userRepository.findById(id);
                if (userOptional.isPresent()) {
                        return userOptional;
                } else {
                        return new ResponseDTO("ERROR", "Usuário não encontrado com ID: " + id);
                }
        }

        @Override
        public ResponseDTO updateUser(Long id, User user) {
                if (!userRepository.existsById(id)) {
                        return new ResponseDTO("ERROR", "ID não encontrado: " + id);
                }
                user.setId(id);
                userRepository.save(user);
                return new ResponseDTO("SUCCESS", "Usuário atualizado");
        }
}
