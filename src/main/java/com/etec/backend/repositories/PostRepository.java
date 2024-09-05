package com.etec.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etec.backend.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
