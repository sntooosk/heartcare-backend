package com.etec.backend.service.impl;

import com.etec.backend.dto.ResponseDTO;
import com.etec.backend.entity.Post;
import com.etec.backend.repository.PostRepository;
import com.etec.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public List<Post> getAll() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(post -> new Post(post.getId(), post.getTitle(), post.getComment()))
                .collect(Collectors.toList());
    }

    @Override
    public Object create(Post post) {
        Post savedPost = postRepository.save(post);
        return new ResponseDTO("OK", "Post criado com sucesso: " + savedPost.getTitle());
    }

    @Override
    public Object update(Long id, Post post) {
        if (!postRepository.existsById(id)) {
            return new ResponseDTO("ERROR", "O ID especificado não existe: " + id);
        }
        post.setId(id);
        Post updatedPost = postRepository.save(post);
        return new ResponseDTO("OK", "Post atualizado com sucesso: " + updatedPost.getTitle());
    }

    @Override
    public Object delete(Long id) {
        if (!postRepository.existsById(id)) {
            return new ResponseDTO("ERROR", "O ID especificado não existe: " + id);
        }
        postRepository.deleteById(id);
        return new ResponseDTO("OK", "O ID especificado foi removido com sucesso: " + id);
    }
}