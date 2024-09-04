package com.etec.backend.service;

import com.etec.backend.entity.Post;

import java.util.List;

public interface PostService {

    List<Post> getAll();

    Object create(Post post);

    Object update(Long id, Post post);

    Object delete(Long id);

}
