package com.etec.backend.controller;

import com.etec.backend.entity.Post;
import com.etec.backend.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/posts", produces = "application/json")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @Operation(summary = "Listar todos os posts", method = "GET")
    @GetMapping("/")
    public List<Post> getAll() {
        return postService.getAll();
    }

    @Operation(summary = "Criar um novo post", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao criar post")
    })
    @PostMapping("/")
    public Object create(@RequestBody Post post) {
        return postService.create(post);
    }

    @Operation(summary = "Excluir um post pelo ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Post não encontrado")
    })
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable Long id) {
        return postService.delete(id);
    }

    @Operation(summary = "Atualizar um post pelo ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar post"),
            @ApiResponse(responseCode = "404", description = "Post não encontrado")
    })
    @PutMapping("/{id}")
    public Object update(@PathVariable Long id, @RequestBody Post post) {
        return postService.update(id, post);
    }
}