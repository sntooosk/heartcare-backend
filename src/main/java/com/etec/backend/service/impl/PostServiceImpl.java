package com.etec.backend.service.impl;

import com.etec.backend.dto.PostResponseDTO;
import com.etec.backend.dto.ResponseDTO;
import com.etec.backend.entity.Post;
import com.etec.backend.repository.PostRepository;
import com.etec.backend.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Operation(summary = "Listar todos os posts", description = "Retorna uma lista com todos os posts cadastrados.")
    @Override
    public List<Post> getAll() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(post -> new Post(post.getId(), post.getTitle(), post.getComment()))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Criar um novo post", description = "Cria um novo post com base nos dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post criado com sucesso", content = { @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = PostResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro ao criar post")
    })
    @Override
    public Object create(Post post) {
        Post savedPost = postRepository.save(post);
        return new PostResponseDTO(savedPost.getTitle(), savedPost.getComment());
    }

    @Operation(summary = "Atualizar um post pelo ID", description = "Atualiza um post existente com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post atualizado com sucesso", content = { @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = PostResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar post"),
            @ApiResponse(responseCode = "404", description = "Post não encontrado")
    })
    @Override
    public Object update(Long id, Post post) {
        if (!postRepository.existsById(id)) {
            return new ResponseDTO("ERROR", "O ID especificado não existe: " + id);
        }
        post.setId(id);
        Post updatedPost = postRepository.save(post);
        return new PostResponseDTO(updatedPost.getTitle(), updatedPost.getComment());
    }

    @Operation(summary = "Excluir um post pelo ID", description = "Exclui um post existente com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post excluído com sucesso", content = { @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Post não encontrado")
    })
    @Override
    public Object delete(Long id) {
        if (!postRepository.existsById(id)) {
            return new ResponseDTO("ERROR", "O ID especificado não existe: " + id);
        }
        postRepository.deleteById(id);
        return new ResponseDTO("OK","O ID especificado foi removido com sucesso: " + id);
    }
}
