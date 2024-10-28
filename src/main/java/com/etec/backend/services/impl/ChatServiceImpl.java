package com.etec.backend.services.impl;

import com.etec.backend.dtos.ChatResponseDTO;
import com.etec.backend.dtos.ResponseDTO;
import com.etec.backend.entities.Chat;
import com.etec.backend.repositories.ChatRepository;
import com.etec.backend.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public List<ChatResponseDTO> getMessages(Long userId1, Long userId2) {
        List<Chat> messages = chatRepository.findMessagesBetweenUsers(userId1, userId2);
        return messages.stream()
                .map(chat -> new ChatResponseDTO(
                        chat.getSenderId(),
                        chat.getRecipientId(),
                        chat.getContent(),
                        chat.getDateSender()))
                .collect(Collectors.toList());
    }

    // Implementação do método para salvar uma nova mensagem de chat com tratamento de resposta
    public ResponseDTO createMessages(Chat chat) {
        try {
            chat.setDateSender(new Date());
            Chat savedChat = chatRepository.save(chat);
            return new ResponseDTO("OK", "Mensagem criada com sucesso: " + savedChat.getId());
        } catch (Exception e) {
            return new ResponseDTO("ERROR", "Erro ao criar mensagem: " + e.getMessage());
        }
    }
}
