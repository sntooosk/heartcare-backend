package com.etec.backend.service;

import com.etec.backend.entity.Post;

public interface PostService {
    
    Object create(Post post);
    Object update(Long id, Post post);
    Object delete(Long id);
    
}
