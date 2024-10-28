package com.etec.backend.controllers;

import com.etec.backend.dtos.ChatRequestDTO;
import com.etec.backend.dtos.ChatResponseDTO;
import com.etec.backend.dtos.ResponseDTO;
import com.etec.backend.entities.Chat;
import com.etec.backend.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/messages/{userId1}/{userId2}")
    public List<ChatResponseDTO> getMessagesBetweenUsers(@PathVariable Long userId1, @PathVariable Long userId2) {
        return chatService.getMessages(userId1, userId2);
    }

    @PostMapping("/send")
    public ResponseDTO sendMessage(@RequestBody Chat chat) {
        return chatService.createMessages(chat);
    }
}
