package com.etec.backend.service.impl;

import com.etec.backend.dto.ResponseDTO;
import com.etec.backend.dto.UserResponseDTO;
import com.etec.backend.entity.User;
import com.etec.backend.repository.UserRepository;
import com.etec.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado", content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @Override
    public Object listId(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional
                .map(user -> new UserResponseDTO(user.getId(), user.getName(), user.getLastname(), user.getDob(),
                        user.getGender(), user.getPhoto()));  
    }
    @Operation(summary = "Atualizar usuário por ID", description = "Atualiza um usuário existente com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso", content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar usuário"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @Override
    public Object update(Long id, User user) {
        if (!userRepository.existsById(id)) {
            return new ResponseDTO("ERROR", "O ID especificado não existe: " + id);
        }

        user.setId(id);
        User updatedUser = userRepository.save(user);
        return new UserResponseDTO(updatedUser.getId(), updatedUser.getName(), updatedUser.getLastname(),
                updatedUser.getDob(), updatedUser.getGender(), updatedUser.getPhoto());
    }
}
