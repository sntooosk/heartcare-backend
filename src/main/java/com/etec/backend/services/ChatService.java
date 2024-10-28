package com.etec.backend.services;

import com.etec.backend.dtos.ChatRequestDTO;
import com.etec.backend.dtos.ChatResponseDTO;
import com.etec.backend.dtos.ResponseDTO;
import com.etec.backend.entities.Chat;

import java.util.List;

public interface ChatService {

    List<ChatResponseDTO> getMessages(Long userId1, Long userId2);

    ResponseDTO createMessages(Chat chat);
}
