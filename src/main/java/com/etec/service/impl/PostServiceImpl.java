package com.etec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.etec.dto.PostResponseDTO;
import com.etec.entities.Post;
import com.etec.exceptions.ResponseDTO;
import com.etec.repositories.PostRepository;
import com.etec.service.PostService;

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
        return new PostResponseDTO(savedPost.getTitle(), savedPost.getComment());
    }

    @Override
    public Object update(Integer id, Post post) {
        if (!postRepository.existsById(id)) {
            return new ResponseDTO("O ID especificado não existe: " + id);
        }
        post.setId(id);
        Post updatedPost = postRepository.save(post);
        return new PostResponseDTO(updatedPost.getTitle(), updatedPost.getComment());
    }

    @Override
    public Object delete(Integer id) {
        if (!postRepository.existsById(id)) {
            return new ResponseDTO("O ID especificado não existe: " + id);
        }
        postRepository.deleteById(id);
        return new ResponseDTO("O ID especificado foi removido com sucesso: " + id);
    }
}
