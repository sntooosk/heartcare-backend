package com.etec.service;

import java.util.List;

import com.etec.entities.Post;

public interface PostService {
    
    List<Post> getAll();
    Object create(Post post);
    Object update(Integer id, Post post);
    Object delete(Integer id);
    
}
