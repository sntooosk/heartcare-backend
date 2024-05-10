package com.etec.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etec.backend.domain.user.UserProfile;


public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}