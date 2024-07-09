package com.etec.controllers;

import org.springframework.web.bind.annotation.*;

import com.etec.entities.Post;
import com.etec.service.PostService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
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
    public Object delete(@PathVariable Integer id) {
        return postService.delete(id);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable Integer id, @RequestBody Post post) {
        return postService.update(id, post);
    }
}