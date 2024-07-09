package com.etec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etec.entities.Post;



public interface PostRepository extends JpaRepository<Post, Integer> {
}
