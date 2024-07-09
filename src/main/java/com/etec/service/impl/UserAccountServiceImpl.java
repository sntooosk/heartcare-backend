package com.etec.service.impl;

import com.etec.dto.UserAccountResponseDTO;
import com.etec.entities.UserAccount;
import com.etec.exceptions.ResponseDTO;
import com.etec.repositories.UserAccountRepository;
import com.etec.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    @Override
    public Object listId(Integer id) {
        Optional<UserAccount> userOptional = userAccountRepository.findById(id);
        return userOptional
                .map(user -> new UserAccountResponseDTO(user.getId(), user.getName(), user.getLastname(), user.getDob(),
                        user.getGender(), user.getPhoto()))
                .orElse(null);
    }

    @Override
    public Object update(Integer id, UserAccount user) {
        if (!userAccountRepository.existsById(id)) {
            return new ResponseDTO("O ID especificado não existe: " + id);
        }

        user.setId(id);
        UserAccount updatedUser = userAccountRepository.save(user);
        return new UserAccountResponseDTO(updatedUser.getId(), updatedUser.getName(), updatedUser.getLastname(),
                updatedUser.getDob(), updatedUser.getGender(), updatedUser.getPhoto());
    }

    @Override
    public Object delete(Integer id) {
        if (!userAccountRepository.existsById(id)) {
            return new ResponseDTO("O ID especificado não existe: " + id);
        }
        
        userAccountRepository.deleteById(id);
        return new ResponseDTO("O ID especificado foi removido com sucesso: " + id);
    }
}
