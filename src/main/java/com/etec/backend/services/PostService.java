package com.etec.backend.services;

import java.util.List;

import com.etec.backend.entities.Post;

public interface PostService {

    List<Post> getAll();

    Object create(Post post);

    Object update(Long id, Post post);

    Object delete(Long id);

}
