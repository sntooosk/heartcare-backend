package com.etec.backend.service;

import com.etec.backend.dto.ResponseDTO;
import com.etec.backend.entity.Post;


public interface PostService {
    
    Object getAll();
    ResponseDTO create(Post post);
    ResponseDTO update(Long id, Post post);
    ResponseDTO delete(Long id);
    
}
