package com.etec.backend.service.impl;

import com.etec.backend.dto.PostResponseDTO;
import com.etec.backend.dto.PressureResponseDTO;
import com.etec.backend.dto.ResponseDTO;
import com.etec.backend.entity.Post;
import com.etec.backend.entity.Pressure;
import com.etec.backend.repository.PostRepository;
import com.etec.backend.repository.PressureRepository;
import com.etec.backend.service.PostService;
import com.etec.backend.service.PressureService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

   
    @Override
    public Object create(Post post) {
        Post savedPost = postRepository.save(post);
        return new PostResponseDTO(savedPost.getTitle(), savedPost.getComment());
    }

    @Override
    public Object update(Long id, Post post) {
        if (!postRepository.existsById(id)) {
            return new ResponseDTO("Não existe esse ID.");
        }
        post.setId(id);
        Post updatedPost = postRepository.save(post);
        return new PostResponseDTO(updatedPost.getTitle(),updatedPost.getComment());
    }

    @Override
    public Object delete(Long id) {
        if (!postRepository.existsById(id)) {
            return new ResponseDTO("Não existe esse ID.");
        }
        postRepository.deleteById(id);
        return new ResponseDTO("Removido com sucesso.");
    }
}