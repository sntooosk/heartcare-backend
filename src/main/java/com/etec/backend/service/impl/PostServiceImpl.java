package com.etec.backend.service.impl;

import com.etec.backend.dto.ResponseDTO;
import com.etec.backend.entity.Post;
import com.etec.backend.repository.PostRepository;
import com.etec.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public Object getAll() {
        var posts = postRepository.findAll();
        return posts.isEmpty() 
            ? new ResponseDTO("ERROR", "Nenhum post encontrado.") 
            : posts;
    }

    @Override
    public ResponseDTO create(Post post) {
        try {
            postRepository.save(post);
            return new ResponseDTO("SUCCESS", "Post criado com sucesso");
        } catch (Exception e) {
            return new ResponseDTO("ERROR", "Erro ao criar post: " + e.getMessage());
        }
    }

    @Override
    public ResponseDTO update(Long id, Post post) {
        if (!postRepository.existsById(id)) {
            return new ResponseDTO("ERROR", "ID não existe: " + id);
        }
        try {
            post.setId(id);
            postRepository.save(post);
            return new ResponseDTO("SUCCESS", "Atualizado com sucesso");
        } catch (Exception e) {
            return new ResponseDTO("ERROR", "Erro ao atualizar post: " + e.getMessage());
        }
    }

    @Override
    public ResponseDTO delete(Long id) {
        if (!postRepository.existsById(id)) {
            return new ResponseDTO("ERROR", "ID não existe: " + id);
        }
        try {
            postRepository.deleteById(id);
            return new ResponseDTO("SUCCESS", "Post removido com sucesso.");
        } catch (Exception e) {
            return new ResponseDTO("ERROR", "Erro ao excluir post: " + e.getMessage());
        }
    }
}
