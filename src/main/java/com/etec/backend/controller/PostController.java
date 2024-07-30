package com.etec.backend.controller;

import com.etec.backend.dto.ResponseDTO;
import com.etec.backend.entity.Post;
import com.etec.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public Object getAll() {
        return postService.getAll();
    }

    @PostMapping
    public ResponseDTO create(@RequestBody Post post) {
        return postService.create(post);
    }

    @DeleteMapping("/{id}")
    public ResponseDTO delete(@PathVariable Long id) {
        return postService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseDTO update(@PathVariable Long id, @RequestBody Post post) {
        return postService.update(id, post);
    }
}
