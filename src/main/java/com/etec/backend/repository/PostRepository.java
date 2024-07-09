package com.etec.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.etec.backend.entity.Post;


public interface PostRepository extends JpaRepository<Post, Long> {
}
