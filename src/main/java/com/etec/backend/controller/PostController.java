package com.etec.backend.controller;

import com.etec.backend.entity.Post;
import com.etec.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public List<Post> getAll() {
        return postService.getAll();
    }

    @PostMapping("/")
    public Object create(@RequestBody Post post) {
        return postService.create(post);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable Long id) {
        return postService.delete(id);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable Long id, @RequestBody Post post) {
        return postService.update(id, post);
    }
}
