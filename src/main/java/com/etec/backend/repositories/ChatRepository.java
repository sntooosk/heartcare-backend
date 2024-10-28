package com.etec.backend.repositories;

import com.etec.backend.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    // Consulta personalizada para buscar mensagens entre dois usuários, independentemente da direção
    @Query("SELECT c FROM Chat c WHERE " +
            "(c.senderId = :userId1 AND c.recipientId = :userId2) OR " +
            "(c.senderId = :userId2 AND c.recipientId = :userId1) " +
            "ORDER BY c.dateSender ASC")
    List<Chat> findMessagesBetweenUsers(
            @Param("userId1") Long userId1,
            @Param("userId2") Long userId2);
}
